package pep.mendez.smvcp1.spring.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pep.mendez.smvcp1.spring.formbeans.EventBean;
import pep.mendez.smvcp1.spring.model.entities.EventEntity;
import pep.mendez.smvcp1.spring.model.service.EventService;
import pep.mendez.smvcp1.utils.UtilityConstants;

/**
 * A key difference between a traditional MVC controller and a RESTful web
 * service controller is the way that the HTTP response body is created. Rather
 * than relying on a view technology to perform server-side rendering of the
 * greeting data to HTML, a RESTful web service controller simply populates and
 * returns an object of any type. The object data will be written directly to
 * the HTTP response as JSON.
 * 
 * all the json are parsed to java objects.
 * 
 * @author pep
 *
 */
//@Controller
@RestController
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

	/*
	 * HttpMessageConverter is responsible for converting the HTTP request
	 * message to an associated java object.
	 * 
	 * ResponseEntity is meant to represent the entire HTTP response. You can
	 * control anything that goes into it: status code, headers, and body.
	 */
//	@RequestMapping(value = "/getEvents", method = RequestMethod.POST)
//	public List<EventBean> getEvents() {
//
//		Collection<EventEntity> events = eventService.findAll();
//		
//		List<EventBean> l_events = new ArrayList<>();
//		
//		for(EventEntity event : events) {
//			EventBean eventEvent = new EventBean(event.getId(), event.getResourceId(), event.getTitle(), event.getStart(), event.getEnd());
//			l_events.add(eventEvent);
//		}
//
//		return l_events;
//
//	}
	
	@RequestMapping(value = "/getEvents", method = RequestMethod.POST)
	public Collection<EventEntity> getEvents() {

		return eventService.findAll();
		
	}

	/*
	 * @ResponseBody indicates a method return value should be bound to the web
	 * response body. Supported for annotated handler methods in Servlet
	 * environments.
	 * 
	 * @RequestBody - Covert Json object to java
	 * 
	 * @ResponseBody- convert Java object to json
	 */
//	@RequestMapping(value = "/getEvents", method = RequestMethod.GET)
//	public @ResponseBody String getEvents(HttpServletResponse response) {
//		
//		JSONArray jsonArray = new JSONArray();
//		for (EventEntity event : eventService.findAll()) {
//			// compulsory: 'id', 'resourceId', 'title', 'start', 'end'
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("id", event.getId().toString());
//			jsonObject.put("resourceId", event.getResourceId());
//			jsonObject.put("title", event.getTitle());
//			jsonObject.put("start", event.getStart().toString());
//			jsonObject.put("end", event.getEnd().toString());
//			jsonArray.add(jsonObject);
//		}
//
//		String json = jsonArray.toJSONString();
//		
//		return json;
//	}

	/*
	 * Just for testing pourposes
	 * 
	 * Checked
	 */
	/*
	 * @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT) public void saveEvent1(Principal
	 * principal) { EventEntity eventEntity = new EventEntity();
	 * eventEntity.setTitle("Event X");
	 * eventEntity.setResourceId(principal.getName()); eventEntity.setStart(new
	 * Date()); eventEntity.setEnd(new Date(System.currentTimeMillis() + 1 * 60
	 * * 60 1000)); eventService.save(eventEntity); }
	 */

	/*
	 * @RequestBody - Covert Json object to java
	 * 
	 * @ResponseBody- convert Java object to json
	 * 
	 * They are annotations of the spring mvc framework and can be used in a
	 * controller to implement smart object serialization and deserialization.
	 * 
	 * If you annotate a method with @ResponseBody, spring will try to convert
	 * its return value and write it to the http response automatically.
	 * 
	 * If you annotate a method's parameter with @RequestBody, spring will try
	 * to convert the content of the incoming request body to your parameter
	 * object on the fly.
	 */
	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	public @ResponseBody EventBean saveEvent(@RequestBody EventBean eventBean,
			Principal principal) {

		logger.debug(eventBean.toString() + principal.toString());
		EventEntity eventEntity = new EventEntity(eventBean.getResourceId(),
				eventBean.getTitle(), eventBean.getStart(), eventBean.getEnd());
		eventEntity.setResourceId(principal.getName());
		eventService.save(eventEntity);
		return eventBean;
	}

	@RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
	public @ResponseBody EventBean deleteEvent(@RequestParam("id") Long id,
			Principal principal) {

		EventEntity eventEntity = eventService.find(id);
		if (eventEntity.getResourceId().equals(principal.getName())) {
			eventService.delete(id);
		}
		return new EventBean(eventEntity.getId(), eventEntity.getResourceId(),
				eventEntity.getTitle(), eventEntity.getStart(),
				eventEntity.getEnd());
	}

	/*
	 * // @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	 * public void saveEvent4(@RequestParam("eventJson") String eventJson,
	 * Principal principal) throws JsonMappingException, JsonParseException,
	 * IOException {
	 * 
	 * // DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * EventEntity eventEntity = new ObjectMapper().readValue(eventJson,
	 * EventEntity.class); eventEntity.setResourceId(principal.getName());
	 * eventEntity.setEnd(new Date(System.currentTimeMillis() + 1 * 60 * 60
	 * 1000)); eventService.save(eventEntity);
	 * 
	 * return; }
	 */

	/*
	 * // @RequestMapping(value = "/saveEvent", method = RequestMethod.POST) //
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT) public @ResponseBody EventEntity
	 * saveEvent5(
	 * 
	 * @RequestParam("eventJson") String eventJson, Principal principal) throws
	 * JsonMappingException, JsonParseException, IOException {
	 * 
	 * // DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * EventEntity eventEntity = new ObjectMapper().readValue(eventJson,
	 * EventEntity.class); eventEntity.setResourceId(principal.getName());
	 * eventEntity.setEnd(new Date(System.currentTimeMillis() + 60 * 60 * 60
	 * 1000));
	 * 
	 * return eventService.save(eventEntity); }
	 */
}

// ****************** notes *********************

// response.setContentType("application/json");
// response.setCharacterEncoding("UTF-8");

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

// eventEntity.setEnd(new Date(System.currentTimeMillis() + 1 * 60 * 60
// * 1000));
// eventService.save(eventEntity);
// eventEntity.setResourceId(principal.getName());
// EventEntity eventEntity = new EventEntity();
// eventEntity.setTitle("Event X");

// eventEntity.setStart(new Date());
// eventEntity.setEnd(new Date(System.currentTimeMillis() + 60 * 60 * 60
// * 1000));
// eventService.save(eventEntity);

// EventEntity eventEntity = new EventEntity();
// eventEntity.setTitle("Event X");
// eventEntity.setStart(new Date());
