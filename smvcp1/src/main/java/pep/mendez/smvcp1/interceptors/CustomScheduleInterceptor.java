package pep.mendez.smvcp1.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
import org.springframework.web.servlet.ModelAndView;
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
public class CustomScheduleInterceptor extends HandlerInterceptorAdapter {

	private int startTime;
	private int endTime;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	/*
	 * Si no es horario de apertura segun configurado en application.properties
	 * se redirige a closed
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		// return super.preHandle(request, response, handler);
		boolean ret = true;
		LocalDateTime ldt = LocalDateTime.now();
		int hourOfDay = ldt.getHourOfDay();
		if (hourOfDay < startTime || hourOfDay > endTime) {
			ret = false;
			response.sendRedirect("closed");
			//request.getRequestDispatcher("closed").forward(request, response);
		} 
		return ret;
		//return false;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

}
