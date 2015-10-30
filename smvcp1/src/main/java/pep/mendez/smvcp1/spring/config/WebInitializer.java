package pep.mendez.smvcp1.spring.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author pep
 *
 *         Interface to be implemented in Servlet 3.0+ environments in order to
 *         configure the ServletContext programmatically -- as opposed to (or
 *         possibly in conjunction with) the traditional web.xml-based approach.
 * 
 *         Implementations of this SPI will be detected automatically by
 *         SpringServletContainerInitializer, which itself is bootstrapped
 *         automatically by any Servlet 3.0 container.
 * 
 */
public class WebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		servletContext.log(servletContext.getServletContextName());
		super.onStartup(servletContext);
	}

	/*
	 * Spring Context
	 * 
	 * @see org.springframework.web.servlet.support.
	 * AbstractAnnotationConfigDispatcherServletInitializer
	 * #getRootConfigClasses()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringContextConfig.class };
	}

	/*
	 * DispatcherServlet config
	 * 
	 * @see org.springframework.web.servlet.support.
	 * AbstractAnnotationConfigDispatcherServletInitializer
	 * #getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { DispatcherServletConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
