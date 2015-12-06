package pep.mendez.smvcp1.spring.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParser;

import pep.mendez.smvcp1.spring.model.entities.EventEntity;
import pep.mendez.smvcp1.spring.model.service.EventService;
import pep.mendez.smvcp1.utils.UtilityConstants;

/**
 * @author pep
 *
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class EventController {

	private static final Logger logger = LoggerFactory
			.getLogger(UtilityConstants.PACKAGE);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;

	@Autowired(required = true)
	private EventService eventService;

	@RequestMapping(value = "/getEvents", method = RequestMethod.GET)
	public @ResponseBody String getEvents(HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		for (EventEntity event : eventService.findAll()) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", event.getId().toString());
			jsonObject.put("resourceId", event.getResourceId());
			jsonObject.put("title", event.getTitle());
			jsonObject.put("start", event.getStart().toString());
			jsonObject.put("end", event.getEnd().toString());
			jsonArray.add(jsonObject);
		}

		String json = jsonArray.toJSONString();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		return json;

		// String json = new Gson().toJson(map);

		// Write JSON string.
		// response.setContentType("application/json");
		// response.setCharacterEncoding("UTF-8");
		// try {
		// response.getWriter().write(json);
		// logger.info(json);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
