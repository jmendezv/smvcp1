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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import pep.mendez.smvcp1.spring.model.entities.Connection;
import pep.mendez.smvcp1.spring.model.entities.User;
import pep.mendez.smvcp1.spring.model.service.AdminService;
import pep.mendez.smvcp1.spring.model.service.ConnectionService;
import pep.mendez.smvcp1.spring.model.service.UserService;

/**
 * @author pep
 *
 */
@Controller
@PropertySources(value = {@PropertySource(name = "props", value = "{classpath:application.properties}", ignoreResourceNotFound = true)})
public class AdminController {

	private static final int SIZE = 5;

	private static final Logger logger = LoggerFactory
			.getLogger(EditController.class);

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
		Collection<User> users = userService.findAll();
		model.addAttribute("principal", principal.getName());
		model.addAttribute("users", users);
		return "admin";
	}

	/*
	 * Registra el acceso en la base de datos
	 */
	@RequestMapping(value = "/adminService")
	public String adminService(ModelMap model, Principal principal, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		Connection connection = new Connection(new Date(), ip);
		User user = userService.findByUserName(principal.getName());
		connection.setUser(user );
		connectionService.save(connection);
//		adminService.performSomeAdminService1(123456);
//		adminService.performSomeAdminService2();
//		adminService.performSomeAdminService3(1);
		// java.lang.ClassCastException porque hay un proxy por medio
		//((AdminService2) adminService).performNewService();
		//model.addAttribute("principal", principal.getName());
		return "redirect:admin";
	}
}