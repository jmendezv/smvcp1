package pep.mendez.smvcp1.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * HandlerInterceptor is basically similar to a Servlet Filter, 
 * but in contrast to the latter it just allows custom pre-processing 
 * with the option of prohibiting the execution of the handler itself, 
 * and custom post-processing. 
 * 
 * Filters are more powerful, for example they allow for 
 * exchanging the request and response objects that are 
 * handed down the chain. 
 * 
 * Note that a filter gets configured in web.xml, 
 * a HandlerInterceptor in the application context.
 * 
 * As a basic guideline, fine-grained handler-related preprocessing tasks 
 * are candidates for HandlerInterceptor implementations, especially factored-out 
 * common handler code and authorization checks. On the other hand, 
 * a Filter is well-suited for request content and view content handling, 
 * like multipart forms and GZIP compression. This typically shows when one 
 * needs to map the filter to certain content types (e.g. images), or to all requests.
 */

/**
 * @author pep mendez
 *
 */
public class CustomScheduleInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Hora de abertura de la web, segun application.properties
	 */
	private int startTime;

	/**
	 * Hora de cierre, segun application.properties
	 */
	private int endTime;

	public CustomScheduleInterceptor(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/*
	 * returns true if the execution chain should proceed with the next
	 * interceptor or the handler itself.
	 * 
	 * Else, DispatcherServlet assumes that this interceptor has already dealt
	 * with the response itself.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		boolean ret = true;
		LocalDateTime ldt = LocalDateTime.now();
		int hourOfDay = ldt.getHourOfDay();
		if (hourOfDay < startTime || hourOfDay > endTime) {
			ret = false;
			response.sendRedirect("closed");
		}
		return ret;
	}

}
