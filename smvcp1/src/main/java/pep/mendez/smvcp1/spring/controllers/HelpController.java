package pep.mendez.smvcp1.spring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class HelpController {

	private static final Logger logger = LoggerFactory
			.getLogger(EditController.class);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/help")
	public String helpPage() {
		return "help";
	}

}
