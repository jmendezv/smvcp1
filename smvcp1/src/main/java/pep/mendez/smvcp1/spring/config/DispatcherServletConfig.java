package pep.mendez.smvcp1.spring.config;

import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import pep.mendez.smvcp1.interceptors.CustomScheduleInterceptor;
import pep.mendez.smvcp1.spring.SpringDef;

/**
 * @author pep
 *
 */
@Configuration
@EnableWebMvc
// TODO When to use ComponentScan
@ComponentScan(basePackageClasses = { SpringDef.class })
@PropertySources({ @PropertySource(value = "classpath:application.properties"), })
public class DispatcherServletConfig extends WebMvcConfigurerAdapter {

	@Autowired
	Environment env;

	org.slf4j.Logger logger = LoggerFactory
			.getLogger(this.getClass().getName());

	/**
	 * Access resource files
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations(
				"classpath:/META-INF/resources/webjars/");
	}

	/**
	 * Configure static content handling
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		int startTime = Integer.parseInt(env.getProperty("startTime"));
		Integer endTime = Integer.valueOf(env.getProperty("endTime"));
		CustomScheduleInterceptor customScheduleInterceptor = new CustomScheduleInterceptor(
				startTime, endTime);
		registry.addInterceptor(customScheduleInterceptor).addPathPatterns(
				"/login");
	}

	/*
	 * Interface to be implemented by objects than can resolve exceptions thrown
	 * during handler mapping or execution, in the typical case to error views.
	 * Implementors are typically registered as beans in the application
	 * context.
	 */
	@Bean(name = "simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {

		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver() {
			@Override
			protected ModelAndView doResolveException(
					HttpServletRequest request, HttpServletResponse response,
					Object handler, Exception ex) {
				ModelAndView mav = super.doResolveException(request, response,
						handler, ex);

				mav.addObject("url", request.getRequestURL());
				mav.addObject("timestamp", new Date());
				mav.addObject("error", ex.getMessage());
				mav.addObject(
						SimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE,
						ex);

				return mav;
			}

			@Override
			protected ModelAndView getModelAndView(String viewName,
					Exception ex, HttpServletRequest request) {
				
				ModelAndView mav = new ModelAndView("exception");
				mav.addObject("url", request.getRequestURL());
				mav.addObject("timestamp", new Date());
				mav.addObject("error", ex.getMessage());
				mav.addObject(
						SimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE,
						ex);

				return mav;
			}

		};

		Properties mappings = new Properties();
		mappings.setProperty("Exception",
				SimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE);
		// mappings.setProperty("InvalidCreditCardException",
		// "creditCardError");

		/*
		 * Set the mappings between exception class names and error view names.
		 */
		exceptionResolver.setExceptionMappings(mappings); // None by default
		exceptionResolver.setDefaultErrorView("exception"); // No default
		exceptionResolver.setOrder(Integer.MAX_VALUE);
		// exceptionResolver.setExceptionAttribute("exception"); // Default is
		// "exception"
		// exceptionResolver.setWarnLogCategory("smvcp1.resolver"); // TODO No
		// default
		return exceptionResolver;
	}

}
