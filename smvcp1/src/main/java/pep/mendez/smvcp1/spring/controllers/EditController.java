package pep.mendez.smvcp1.spring.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pep.mendez.smvcp1.formbeans.UserEditBean;
import pep.mendez.smvcp1.spring.model.entities.Authority;
import pep.mendez.smvcp1.spring.model.entities.User;
import pep.mendez.smvcp1.spring.model.service.AuthorityService;
import pep.mendez.smvcp1.spring.model.service.UserService;

@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class EditController {

	private static final Logger logger = LoggerFactory
			.getLogger(EditController.class);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;

	@Autowired
	private AuthorityService authorityService;

	@Autowired(required = true)
	private UserService userService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = { "id" })
	public String editPage(@RequestParam(value = "id") long id, ModelMap model) {
		User user = userService.findOne(id);
		UserEditBean userEditBean = new UserEditBean(user.getUserName(),
				user.isEnabled());
		for (Authority authority : user.getAuthorities()) {
			userEditBean.addRole(authority.getAuthority());
		}
		List<String> allRoles = Arrays.asList("ROLE_USER", "ROLE_ADMIN",
				"ROLE_ROOT");
		model.addAttribute("allRoles", allRoles);
		model.addAttribute("userEditBean", userEditBean);
		return "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = { "action" })
	public String editForm(
			@Valid @ModelAttribute("userEditBean") UserEditBean userEditBean,
			BindingResult bindingResult,
			@RequestParam(value = "action") String action) {

		logger.debug(userEditBean.toString());

		if (action.equals("cancel")) {
			return "redirect:admin";
		}
		if (bindingResult.hasErrors()) {
			return "edit";
		}

		String userName = userEditBean.getUserName();
		User user = userService.findByUserName(userName);
		user.setEnabled(userEditBean.isEnabled());
		List<String> roles = userEditBean.getRoles();
		//if (roles != null) {
			authorityService.deleteAllAuthorities(user.getUserName());
//			for (String role : roles) {
//				logger.debug(role);
//				logger.debug(userName);
//				Authority authority = new Authority(userName, role);
//				logger.debug(authority.toString());
//				authority.setUser(user);
//				user.add(authority);
//			}
		//}
		userService.save(user);

		return "redirect:admin";
	}

}
