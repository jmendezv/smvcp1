package pep.mendez.smvcp1.spring.controllers;

import java.util.Collection;

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
import org.springframework.web.bind.annotation.RequestParam;

import pep.mendez.smvcp1.spring.model.entities.Connection;
import pep.mendez.smvcp1.spring.model.entities.User;
import pep.mendez.smvcp1.spring.model.service.UserService;

/**
 * @author pep
 *
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class ConnectionsController {

	private static final Logger logger = LoggerFactory
			.getLogger(ConnectionsController.class);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;

	@Autowired(required = true)
	private UserService userService;

	@RequestMapping(value = "/connections", method = RequestMethod.GET, params = { "id" })
	public String editPage(@RequestParam(value = "id") long id, ModelMap model) {
		User user = userService.findOne(id);
		// org.hibernate.LazyInitializationException: failed to lazily
		// initialize a collection
		// could not initialize proxy - no Session
		// Hibernate.initialize(user.getConnections());

		Collection<Connection> connections = user.getConnections();

		// Comparator<Connection> comparator = (Connection c1, Connection c2) ->
		// c2.getTimeIn().compareTo(c1.getTimeIn());
		// Stream<Connection> stream =
		// connections.stream().limit(5).sorted(comparator);
		// Connection[] aconnections = (Connection[]) stream.toArray();
		// Collection<Connection> c = Arrays.asList(aconnections);

		// throw new RuntimeException("provoked exception from " +
		// this.getClass().getName());

		model.addAttribute("connections", connections);
		return "connections";
	}

}
