package pep.mendez.smvcp1.spring.controllers;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import pep.mendez.smvcp1.spring.model.entities.ConnectionEntity;
import pep.mendez.smvcp1.spring.model.entities.UserEntity;
import pep.mendez.smvcp1.spring.model.service.AdminService;
import pep.mendez.smvcp1.spring.model.service.ConnectionService;
import pep.mendez.smvcp1.spring.model.service.UserService;
import pep.mendez.smvcp1.utils.UtilityConstants;

/**
 * @author pep
 *
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = "{classpath:application.properties}", ignoreResourceNotFound = true) })
public class AdminController {

	private static final int SIZE = 5;

	private static final Logger logger = LoggerFactory
			.getLogger(UtilityConstants.PACKAGE);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;

	@Autowired
	private AdminService adminService;

	@Autowired
	private ConnectionService connectionService;

	@Autowired(required = true)
	private UserService userService;

	@RequestMapping(value = "/admin")
	public String adminPage(ModelMap model, Principal principal) {
		Collection<UserEntity> users = userService.findAll();
		model.addAttribute("principal", principal.getName());
		model.addAttribute("users", users);
		return "admin";
	}

	/*
	 * Registra el acceso en la base de datos
	 */
	@RequestMapping(value = "/adminService")
	@ResponseStatus(value = HttpStatus.CONTINUE)
	// @ResponseBody
	public void adminService(ModelMap model, Principal principal,
			HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		ConnectionEntity connection = new ConnectionEntity(new Date(), ip);
		UserEntity user = userService.findByUserName(principal.getName());
		connection.setUser(user);
		connectionService.save(connection);
		// adminService.performSomeAdminService1(123456);
		// adminService.performSomeAdminService2();
		// adminService.performSomeAdminService3(1);
		// java.lang.ClassCastException porque hay un proxy por medio
		// ((AdminService2) adminService).performNewService();
		// model.addAttribute("principal", principal.getName());
		// return "redirect:admin";
	}
}
