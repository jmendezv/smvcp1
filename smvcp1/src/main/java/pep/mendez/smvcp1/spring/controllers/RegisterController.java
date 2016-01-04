package pep.mendez.smvcp1.spring.controllers;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pep.mendez.smvcp1.spring.formbeans.UserRegistrationBean;
import pep.mendez.smvcp1.spring.model.entities.AuthorityEntity;
import pep.mendez.smvcp1.spring.model.entities.UserEntity;
import pep.mendez.smvcp1.spring.model.service.UserService;
import pep.mendez.smvcp1.utils.Utility;
import pep.mendez.smvcp1.utils.UtilityConstants;

/**
 * @author pep
 *
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class RegisterController {

	private static final Logger logger = LoggerFactory
			.getLogger(UtilityConstants.PACKAGE);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;

	@Autowired(required = true)
	private UserService userService;

	@Autowired(required = true)
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	JavaMailSender mailSender;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(UserRegistrationBean userRegistrationBean) {
		// userRegistrationBean is added to de model automatically
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerForm(
			@Valid @ModelAttribute("userRegistrationBean") UserRegistrationBean userRegistrationBean,
			BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action,
			ModelMap model) {

		logger.debug(userRegistrationBean.toString());

		if (action.equals("cancel")) {
			return "login";
		}

		if (bindingResult.hasErrors()) {
			return "register";
		}

		String userName = userRegistrationBean.getUserName();

		UserEntity user = userService.findByUserName(userName);

		// Error user ja existeix
		if (user != null && !user.isEnabled()) {
			bindingResult.addError(new ObjectError("register.error.duplicated",
					messageSource.getMessage("register.error.duplicated", null,
							null)));
			return "register";
		}

		String encodedPassword = passwordEncoder.encode(userRegistrationBean
				.getPassword());

		// new user
		if (user == null) {
			user = new UserEntity(userName, encodedPassword);
		}
		// existing user, assign new password
		else {
			user.setPassword(encodedPassword);
		}

		// assign user role if no authority granted
		if (user.getAuthorities().size() == 0) {
			AuthorityEntity authority = new AuthorityEntity(userName,
					"ROLE_USER");
			authority.setUser(user);
			user.add(authority);
		}

		userService.save(user);

		String md5Hex = DigestUtils.md5DigestAsHex((userName + Utility.SALT)
				.getBytes());

		// moved to message.properties
		// StringBuilder body = new StringBuilder(
		// UtilityConstants.VALIDATE_MESSAGE_1);
		StringBuilder body = new StringBuilder(messageSource.getMessage("register.controller.msg1",
				new String[] {}, Locale.getDefault()));
		
		// moved to messages.properties
//		body.append(user.getId()).append(";d=").append(md5Hex)
//				.append(UtilityConstants.VALIDATE_MESSAGE_2);
		
		body.append(user.getId()).append(";d=").append(md5Hex)
		.append(messageSource.getMessage("register.controller.msg2",
				new String[] {}, Locale.getDefault()));
		
		String from = env.getProperty("mailserver.username");

		Utility.sendEmail(mailSender, from, userName, messageSource.getMessage("register.controller.subject",
				new String[] {}, Locale.getDefault()),
				body.toString());

		model.addAttribute("registered", "yes");

		// registered ok, so go to index page now
		
		return "index";
	}

}
