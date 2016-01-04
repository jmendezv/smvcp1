package pep.mendez.smvcp1.spring.controllers;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;

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
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pep.mendez.smvcp1.spring.model.entities.ResetEntity;
import pep.mendez.smvcp1.spring.model.entities.UserEntity;
import pep.mendez.smvcp1.spring.model.service.UserService;
import pep.mendez.smvcp1.utils.Utility;
import pep.mendez.smvcp1.utils.UtilityConstants;

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
	@RequestMapping(value = "/pwdreseted/{id}")
	public ModelAndView validate(@PathVariable(value = "id") long id,
			@MatrixVariable(value = "d", required = true) String userNameHash,
			@MatrixVariable(value = "c", required = true) long resetCode) {

		UserEntity user = userService.findOne(id);

		// assume everything is ok
		String msgValidation = messageSource.getMessage(
				"resetpwd.controller.msg.ok",
				new String[] { user.getUserName() }, Locale.getDefault()); // env.getProperty("msg.validation.ok");

		// String msgValidationError = messageSource.getMessage(
		// "resetpwd.controller.msg.error", null, Locale.getDefault()); //
		// env.getProperty("msg.validation.error");

		// no resets registered
		if (user.getAuthorities().isEmpty()) {
			msgValidation = messageSource.getMessage(
					"resetpwd.controller.error.doesnotexist",
					new String[] { user.getUserName() }, Locale.getDefault());
			// at least one reset registered
		} else {
			// Hash del userName + SALT
			String md5Hex = DigestUtils
					.md5DigestAsHex((user.getUserName() + Utility.SALT)
							.getBytes());
			// first level of security
			// userName from email match this userName
			if (userNameHash.equals(md5Hex)) {
				// get last
				// List<Reset> resets = ((List<Reset>) user.getResets());
				// Reset reset = resets.get(resets.size() - 1);
				// get last streams
				Optional<ResetEntity> optReset = user.getResets().stream()
						.reduce((a, b) -> b);
				ResetEntity reset = optReset.get();
				// second level of security
				// not expired yet
				if (new Date().before(reset.getDateExpiry())) {
					// third level of securty
					// reset code must match
					if (reset.getResetCode() == resetCode) {
						// from apache commons lang3
						String newPwd = RandomStringUtils
								.randomAlphanumeric(10);

						String encodedPassword = passwordEncoder.encode(newPwd);
						user.setPassword(encodedPassword);

						// StringBuilder body = new StringBuilder(
						// UtilityConstants.RESETED_PASSWORD_MESSAGE_1);

						StringBuilder body = new StringBuilder(
								messageSource
										.getMessage(
												"validateresetpwd.controller.validated",
												null, Locale.getDefault()));

						body.append(user.getUserName()).append("/")
								.append(newPwd);

						String from = env.getProperty("mailserver.replyTo");

						String subject = messageSource.getMessage(
								"validateresetpwd.controller.subject", null,
								Locale.getDefault());

						Utility.sendEmail(mailSender, from, user.getUserName(),
								subject, body.toString());
						userService.save(user);

					}
					// code manipulated
					else {
						msgValidation = messageSource.getMessage(
								"resetpwd.controller.error.manipulated",
								new String[] { user.getUserName() },
								Locale.getDefault());
					}
					// expired
				} else {
					msgValidation = messageSource.getMessage(
							"resetpwd.controller.error.expired",
							new String[] { user.getUserName() },
							Locale.getDefault());
				}
			}

		}

		ModelAndView mav = new ModelAndView("validatedresetpwd", "message",
				msgValidation);

		return mav;

	}

}
