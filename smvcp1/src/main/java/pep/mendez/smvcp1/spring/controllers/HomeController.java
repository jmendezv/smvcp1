package pep.mendez.smvcp1.spring.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pep.mendez.smvcp1.spring.model.entities.User;
import pep.mendez.smvcp1.spring.model.service.UserService;

// '123456' $2a$10$0NNbiILNyNzspF8dg95s7eQuQS.pPyKuUeNbhQ4pHezM0dwK.1Wje
/**
 * @author pep
 *
 */
@Controller
@RequestMapping(value = "/")
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class HomeController {

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

	@RequestMapping(method = RequestMethod.GET)
	public String homePage(ModelMap model, Principal principal,
			HttpServletRequest request, HttpSession session) {
		User user = userService.findByUserName(principal.getName());
		model.addAttribute("user", user);
		// Connection connection = new Connection();
		// connection.setTimeIn(new Date());
		// connection.setIp(request.getRemoteAddr());
		// session.setAttribute("connection", connection);
		// if (user.getProfile() == null) {
		// return "redirect:/profile";
		// }
		return "home";
	}

}
