package pep.mendez.smvcp1.spring.controllers;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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

/**
 * @author pep
 *
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class AccessController {

	private static final Logger logger = LoggerFactory
			.getLogger(EditController.class);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;

	@RequestMapping("/403")
	public String accessDenied(Principal principal, ModelMap model,
			HttpServletRequest request) {

		String msg = "";
		String warning = "";
		if (principal == null) {
			msg = messageSource.getMessage("403.errorMessage",
					new String[] { messageSource.getMessage("accesscontroller.anonymous", null, Locale.getDefault()) }, Locale.getDefault());
		} else {
			msg = messageSource.getMessage("403.errorMessage",
					new String[] { principal.getName() }, Locale.getDefault());
		}
		warning = messageSource.getMessage("403.warning",
				new String[] { request.getRemoteAddr() }, Locale.getDefault());
		
		model.addAttribute("errorMessage", msg);
		model.addAttribute("remoteAddress", warning);

		return "403";
	}

}
