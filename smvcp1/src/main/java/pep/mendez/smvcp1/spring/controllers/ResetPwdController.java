package pep.mendez.smvcp1.spring.controllers;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.validation.Valid;

import org.joda.time.DateTimeConstants;
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

import pep.mendez.smvcp1.spring.formbeans.ResetPwdBean;
import pep.mendez.smvcp1.spring.model.entities.ResetEntity;
import pep.mendez.smvcp1.spring.model.entities.UserEntity;
import pep.mendez.smvcp1.spring.model.service.UserService;
import pep.mendez.smvcp1.utils.Utility;
import pep.mendez.smvcp1.utils.UtilityConstants;

/**
 * @author pep
 * 
 *         This controller send an email with encrypted user name and a code
 *
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class ResetPwdController {

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

	@RequestMapping(value = "/resetpwd", method = RequestMethod.GET)
	public String resetPwdPage(ResetPwdBean resetPwdBean) {
		// userRegistrationBean is added to the model automatically
		return "resetpwd";
	}

	@RequestMapping(value = "/resetpwd", method = RequestMethod.POST)
	public String resetPwdForm(
			@Valid @ModelAttribute("resetPwdBean") ResetPwdBean resetPwdBean,
			BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action) {

		logger.debug(resetPwdBean.toString());

		if (action.equals("cancel")) {
			return "login";
		}

		if (bindingResult.hasErrors()) {
			return "resetpwd";
		}

		String userName = resetPwdBean.getUserName();

		UserEntity user = userService.findByUserName(userName);

		// not enabled means discarded
		if (user != null && !user.isEnabled()) {
			user = null;
		}

		// Error user no existeix
		// TODO show message como si nada!!!
		if (user == null) {
			bindingResult.addError(new ObjectError(
					"resetpwd.error.doesnotexist", messageSource.getMessage(
							"resetpwd.error.doesnotexist",
							new String[] { userName }, null)));
			return "resetpwd";
		}

		int hours = Integer.valueOf(env.getProperty(
				"resetpwdcontroller.expiryhours", "24"));

		ResetEntity reset = new ResetEntity(new Random().nextLong(), new Date(
				System.currentTimeMillis() + DateTimeConstants.MILLIS_PER_HOUR
						* hours));
		reset.setUser(user);
		user.add(reset);
		userService.save(user);

		String userNameMd5Hex = DigestUtils
				.md5DigestAsHex((userName + Utility.SALT).getBytes());

		// http://localhost:8080/smvcp1/resetpwd/1;d=<username_digest>;c=<resert_code>
		// http://localhost:8080/smvcp1/resetpwd/1;d=AB468EDAB33D49032CE198C;c=3453456786656
		StringBuilder body = new StringBuilder(
				UtilityConstants.RESET_PASSWORD_MESSAGE_1);
		body.append(user.getId()).append(";d=").append(userNameMd5Hex)
				.append(";c=").append(reset.getResetCode())
				.append(UtilityConstants.RESET_PASSWORD_MESSAGE_2);

		String from = env.getProperty("mailserver.replyTo");

		String subject = messageSource.getMessage("resetpwdcontroller.subject",
				null, Locale.getDefault());

		Utility.sendEmail(mailSender, from, userName, subject, body.toString());

		return "login";
	}

}
