package pep.mendez.smvcp1.spring.controllers;

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
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pep.mendez.smvcp1.spring.model.entities.User;
import pep.mendez.smvcp1.spring.model.service.UserService;
import pep.mendez.smvcp1.utils.Utility;

@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class ValidateController {

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

	@RequestMapping(value = "/validate/{id}", method = RequestMethod.GET)
	public ModelAndView validate(@PathVariable(value = "id") long id,
			@MatrixVariable(value = "d", required = true) String digest) {

		String msgValidated = messageSource.getMessage("validate.controller.msg.ok",
				null, null); // env.getProperty("msg.validation.ok");
		String msgValidationError = messageSource.getMessage(
				"validate.controller.msg.error", null, null); // env.getProperty("msg.validation.error");
		ModelAndView mav = new ModelAndView("validate", "message",
				msgValidationError);
		mav.addObject("user", "");
		User user = userService.findOne(id);
		String encodedPassword = user.getPassword();
		String md5Hex = DigestUtils.md5DigestAsHex((user.getUserName() + Utility.SALT).getBytes());
		if (md5Hex.equals(digest)) {
			user.setEnabled(true);
			userService.save(user);
			mav.addObject("message", msgValidated);
			mav.addObject("user", user.getUserName());
		}
		return mav;

	}
}
