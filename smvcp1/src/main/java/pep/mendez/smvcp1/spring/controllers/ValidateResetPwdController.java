package pep.mendez.smvcp1.spring.controllers;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pep.mendez.smvcp1.spring.model.entities.Reset;
import pep.mendez.smvcp1.spring.model.entities.User;
import pep.mendez.smvcp1.spring.model.service.UserService;

/**
 * 
 * @author pep
 * 
 *         This controller validates data from email link and after success
 *         sends an email back with a new password. It will also save the
 *         encrypter password in the db
 *
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class ValidateResetPwdController {
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

	// en /validate/1;d=125de32156abefde1 id=1 digest=125de32156abefde1
	@RequestMapping(value = "/resetpwd/{id}", method = RequestMethod.GET)
	public ModelAndView validate(@PathVariable(value = "id") long id,
			@MatrixVariable(value = "d", required = true) long resetCode) {

		String msgValidated = messageSource.getMessage(
				"resetpwd.controller.msg.ok", null, null); // env.getProperty("msg.validation.ok");
		String msgValidationError = messageSource.getMessage(
				"resetpwd.controller.msg.error", null, null); // env.getProperty("msg.validation.error");
		ModelAndView mav = new ModelAndView("validatedresetpwd", "message",
				msgValidationError);
		mav.addObject("user", "");
		User user = userService.findOne(id);

		// no resets registered
		if (user.getAuthorities().isEmpty()) {

			// at least one reset registered
		} else {
			List<Reset> resets = (List<Reset>) user.getResets();
			// Last reset record
			Reset reset = resets.get(resets.size() - 1);
			// not expired yet
			if (new Date().before(reset.getDateExpiry())) {
				if (reset.getResetCode() == resetCode) {
					// generar password, encriptarlo y guardarlo.
					// enviar el nuevo password por correo
					// TODO !!!!

					// Hash del userName + SALT
					// String md5Hex = DigestUtils.md5DigestAsHex((user
					// .getUserName() + Utility.SALT).getBytes());
					// if (md5Hex.equals("")) {
					// user.setEnabled(true);
					// userService.save(user);
					// mav.addObject("message", msgValidated);
					// mav.addObject("user", user.getUserName());
					// }
				}
				// expired
			} else {

			}

		}

		return mav;

	}

	private CharSequence genPwd() {
		CharSequence pwd = RandomStringUtils.random(8);
		return pwd;
	}
}
