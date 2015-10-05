package pep.mendez.smvcp1.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * A sub-class of WebMvcConfigurationSupport that detects and delegates to all
 * beans of type WebMvcConfigurer allowing them to customize the configuration
 * provided by WebMvcConfigurationSupport.
 * 
 * This is the class actually imported by @EnableWebMvc.
 * 
 * All this just to enable matrix variables, /user/{name};d=000000
 * 
 * @author pep
 *
 */
@Configuration
public class PathWebAppConfig extends DelegatingWebMvcConfiguration {

	@Bean
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping handlerMapping = super
				.requestMappingHandlerMapping();
		/*
		 * Set if ";" (semicolon) content should be stripped from the request
		 * URI.
		 * 
		 * The default value is true.
		 */
		handlerMapping.setRemoveSemicolonContent(false);
		/*
		 * Whether to match to URLs irrespective of the presence of a trailing
		 * slash. If enabled a method mapped to "/users" also matches to
		 * "/users/".
		 * 
		 * The default value is true.
		 */
		handlerMapping.setUseTrailingSlashMatch(false);
		return handlerMapping;
	}

	/*
	 * Returns a RequestMappingHandlerAdapter for processing requests through
	 * annotated controller methods
	 */
	@Bean
	@Override
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter handlerApapter = super
				.requestMappingHandlerAdapter();
		/*
		 * Setting this flag to true guarantees the "default" model is never
		 * used in a redirect scenario even if a RedirectAttributes argument is
		 * not declared
		 */
		handlerApapter.setIgnoreDefaultModelOnRedirect(true);
		return handlerApapter;
	}
}