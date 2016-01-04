package pep.mendez.smvcp1.spring.controllers;

import java.util.Locale;

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
public class ValidateNewUserController {

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

	// en /validate/1;d=125de32156abefde1 id=1 digest=125de32156abefde1
	@RequestMapping(value = "/validate/{id}", method = RequestMethod.GET)
	public ModelAndView validate(@PathVariable(value = "id") long id,
			@MatrixVariable(value = "d", required = true) String digest) {

		UserEntity user = userService.findOne(id);

		// assume something went wrong
		String msgValidation = messageSource.getMessage(
				"validate.controller.msg.error",
				new String[] { user.getUserName() }, Locale.getDefault());

		// Calculo el hash del userName + SALT
		String md5Hex = DigestUtils
				.md5DigestAsHex((user.getUserName() + Utility.SALT).getBytes());
		
		// Lo comparo con el hash que me llega
		if (md5Hex.equals(digest)) {
			user.setEnabled(true);
			userService.save(user);
			msgValidation = messageSource.getMessage(
					"validate.controller.msg.ok",
					new String[] { user.getUserName() }, Locale.getDefault());
		}

		ModelAndView mav = new ModelAndView("validatednewuser", "message",
				msgValidation);

		return mav;
	}
}
