package pep.mendez.smvcp1.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pep.mendez.smvcp1.spring.model.entities.UserEntity;
import pep.mendez.smvcp1.spring.model.service.UserService;
import pep.mendez.smvcp1.utils.UtilityConstants;

@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class UnsubscribeController {

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

	@RequestMapping(value = "/unsubscribe", method = RequestMethod.GET, params = { "username" })
	public String logout(
			@RequestParam(value = "username", required = true) String userName,
			HttpServletRequest request, HttpServletResponse response) {

		UserEntity user = userService.findByUserName(userName);
		user.setEnabled(false);
		userService.save(user);

		logout(request, response);

		return "redirect:login?logout";
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			// new SecurityContextLogoutHandler().logout(request, null, null);
		}
		SecurityContextHolder.getContext().setAuthentication(null);
	}

}
