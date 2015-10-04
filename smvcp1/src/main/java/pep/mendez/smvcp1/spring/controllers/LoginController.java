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

@Controller
@PropertySources(value = {@PropertySource(name = "props", value = {"classpath:application.properties"}, ignoreResourceNotFound = true)})
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(EditController.class);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String loginPage(ModelMap model) {
		return "login";
	}
	
}
