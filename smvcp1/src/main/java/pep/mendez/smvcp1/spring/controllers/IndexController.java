package pep.mendez.smvcp1.spring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pep.mendez.smvcp1.utils.UtilityConstants;

/**
 * @author pep
 *
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }, ignoreResourceNotFound = true) })
public class IndexController {

	private static final Logger logger = LoggerFactory
			.getLogger(UtilityConstants.PACKAGE);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/")
	public String indexPage() {

		// UserEntity user = userService.findByUserName(principal.getName());
		// long total = userService.countUsers();
		//
		// logger.info(user.toString());
		//
		// model.addAttribute("user", user);
		// model.addAttribute("total", total);
		// Connection connection = new Connection();
		// connection.setTimeIn(new Date());
		// connection.setIp(request.getRemoteAddr());
		// session.setAttribute("connection", connection);
		// if (user.getProfile() == null) {
		// return "redirect:/profile";
		// }
		// return "home";
		return "redirect:/index";
	}

	@RequestMapping(value = "/index")
	public String indexPage(ModelMap model) {
		return "index";
	}

}
