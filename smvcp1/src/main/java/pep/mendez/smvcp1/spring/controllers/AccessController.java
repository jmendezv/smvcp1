package pep.mendez.smvcp1.spring.controllers;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessController {

	@Autowired
	MessageSource messageSource;

	@RequestMapping("/403")
	public String accessDenied(Principal principal, ModelMap model, HttpServletRequest request) {

		String msg = messageSource.getMessage("403.errorMessage",
				new String[] { principal.getName() }, Locale.getDefault());
		String warning = messageSource.getMessage("403.warning",
				new String[] { request.getRemoteAddr() }, Locale.getDefault());
		model.addAttribute("errorMessage", msg);
		model.addAttribute("remoteAddress", warning);

		return "403";
	}

}
