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
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pep.mendez.smvcp1.spring.formbeans.UserRegistrationBean;
import pep.mendez.smvcp1.spring.model.entities.Authority;
import pep.mendez.smvcp1.spring.model.entities.User;
import pep.mendez.smvcp1.spring.model.service.UserService;
import pep.mendez.smvcp1.utils.Utility;
import pep.mendez.smvcp1.utils.UtilityConstants;

@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class ChangePwdController {
	private static final Logger logger = LoggerFactory
			.getLogger(RegisterController.class);

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

	@RequestMapping(value = "/changepwd", method = RequestMethod.GET, params = { "username" })
	public String registerPage(UserRegistrationBean userRegistrationBean,
			@RequestParam(value = "username", required = true) String userName) {
		// userRegistrationBean is added to de model automatically
		userRegistrationBean.setUserName(userName);
		return "changepwd";
	}

	@RequestMapping(value = "/changepwd", method = RequestMethod.POST, params = { "action" })
	public String registerForm(
			@Valid @ModelAttribute("userRegistrationBean") UserRegistrationBean userRegistrationBean,
			BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action,
			ModelMap model) {

		logger.debug(userRegistrationBean.toString());
		
		String userName = userRegistrationBean.getUserName();

		User user = userService.findByUserName(userName);
		
		model.addAttribute("user", user);

		if (action.equals("cancel")) {
			return "home";
		}

		if (bindingResult.hasErrors()) {
			return "changepwd";
		}

		// Error user no existeix
		if (user == null) {
//			bindingResult.addError(new ObjectError("register.error.duplicated",
//					messageSource.getMessage("register.error.duplicated", null,
//							null)));
			
			return "register";
		}
		
		if (!user.isEnabled()) {
//			bindingResult.addError(new ObjectError("register.error.duplicated",
//					messageSource.getMessage("register.error.duplicated", null,
//							null)));
			
			return "register";
		}

		String encodedPassword = passwordEncoder.encode(userRegistrationBean
				.getPassword());
		user.setPassword(encodedPassword);

		userService.save(user);


		return "home";
	}

}
