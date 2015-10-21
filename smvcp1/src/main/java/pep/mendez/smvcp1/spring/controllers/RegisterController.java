package pep.mendez.smvcp1.spring.controllers;

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
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pep.mendez.smvcp1.spring.formbeans.UserRegistrationBean;
import pep.mendez.smvcp1.spring.model.entities.Authority;
import pep.mendez.smvcp1.spring.model.entities.User;
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
			@RequestParam(value = "action", required = true) String action) {

		logger.debug(userRegistrationBean.toString());

		if (action.equals("cancel")) {
			return "login";
		}

		if (bindingResult.hasErrors()) {
			return "register";
		}

		String userName = userRegistrationBean.getUserName();

		User user = userService.findByUserName(userName);

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
			user = new User(userName, encodedPassword);
		}
		// existing user, assign new password
		else {
			user.setPassword(encodedPassword);
		}

		// assign user role if no authority granted
		if (user.getAuthorities().size() == 0) {
			Authority authority = new Authority(userName, "ROLE_USER");
			authority.setUser(user);
			user.add(authority);
		}

		userService.save(user);

		String md5Hex = DigestUtils.md5DigestAsHex((userName + Utility.SALT)
				.getBytes());

		StringBuilder body = new StringBuilder(
				UtilityConstants.VALIDATE_MESSAGE_1);
		body.append(user.getId()).append(";d=").append(md5Hex)
				.append(UtilityConstants.VALIDATE_MESSAGE_2);

		String from = env.getProperty("mailserver.replyTo");

		Utility.sendEmail(mailSender, from, userName, "Registro",
				body.toString());

		return "login";
	}

}
