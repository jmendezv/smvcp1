package pep.mendez.smvcp1.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pep.mendez.smvcp1.exceptions.UserNotFoundException;
import pep.mendez.smvcp1.spring.model.entities.User;
import pep.mendez.smvcp1.spring.model.service.UserService;

/*
 * A convenience annotation that is itself annotated with @Controller and @ResponseBody.
 * 
 * @ResponseBody Annotation that indicates a method return value should be bound to the web response body
 */
@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	/*
	 * Annotation for handling exceptions in specific handler classes and/or handler methods
	 */
	@ExceptionHandler(value = UserNotFoundException.class)
	/*
	 * Marks a method or exception class with the status code() and reason() that should be returned.
	 */
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Error userNotFoundHandler(UserNotFoundException e) {
		return new Error(e.getMessage());
	}

	/*
	 * PathVariable truncates at dot position
	 */
	@RequestMapping(value = "/user/{name:.+}", method = RequestMethod.GET)
	public User userByName(@PathVariable("name") String name) {
		User user = userService.findByUserName(name);
		if (user == null) {
			throw new UserNotFoundException(name + " no se encuentra");
		}
		return user;
	}
}
