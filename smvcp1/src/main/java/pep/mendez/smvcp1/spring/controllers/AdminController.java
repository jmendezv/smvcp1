package pep.mendez.smvcp1.spring.controllers;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import pep.mendez.smvcp1.spring.model.entities.User;
import pep.mendez.smvcp1.spring.model.service.AdminService;
import pep.mendez.smvcp1.spring.model.service.AdminService2;
import pep.mendez.smvcp1.spring.model.service.UserService;

@Controller
public class AdminController {

	private static final int SIZE = 5;

	@Autowired
	private AdminService adminService;

	@Autowired(required = true)
	private UserService userService;

	@RequestMapping(value = "/admin")
	public String adminPage(ModelMap model, Principal principal) {
		Collection<User> users = userService.findAll();
		model.addAttribute("principal", principal.getName());
		model.addAttribute("users", users);
		return "admin";
	}

	@RequestMapping(value = "/adminService")
	public String adminService(ModelMap model, Principal principal) {
		adminService.performSomeAdminService1(123456);
		adminService.performSomeAdminService2();
		adminService.performSomeAdminService3(1);
		((AdminService2) adminService).performNewService();
		model.addAttribute("principal", principal.getName());
		return "admin";
	}
}
