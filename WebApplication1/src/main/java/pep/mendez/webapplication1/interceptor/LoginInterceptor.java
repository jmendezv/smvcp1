package pep.mendez.webapplication1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Callback after completion of request processing, that is, after rendering
	 * the view Will only be called if this interceptor's preHandle method has
	 * successfully completed and returned true! Will be invoked on each
	 * interceptor in the chain in reverse order, so the first interceptor will
	 * be the last to be invoked
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * Intercept the execution of a handler. Called after HandlerAdapter
	 * actually invoked the handler, but before the DispatcherServlet renders
	 * the view. Can expose additional model objects to the view via the given
	 * ModelAndView.
	 * 
	 * With this method, each interceptor can post-process an execution, getting
	 * applied in inverse order of the execution chain.
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * Intercept the execution of a handler. Called after HandlerMapping
	 * determined an appropriate handler object, but before HandlerAdapter
	 * invokes the handler.
	 * 
	 * Returns: true if the execution chain should proceed with the next
	 * interceptor or the handler itself. Else, DispatcherServlet assumes that
	 * this interceptor has already dealt with the response itself.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		return super.preHandle(request, response, handler);
	}

}
