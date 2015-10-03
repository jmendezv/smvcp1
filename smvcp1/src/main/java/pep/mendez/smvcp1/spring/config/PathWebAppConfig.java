package pep.mendez.smvcp1.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * A sub-class of WebMvcConfigurationSupport that detects and delegates to all
 * beans of type WebMvcConfigurer allowing them to customize the configuration
 * provided by WebMvcConfigurationSupport. This is the class actually imported
 * by @EnableWebMvc.
 * 
 * All this just to enable matrix variables
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
		handlerMapping.setRemoveSemicolonContent(false);
		handlerMapping.setUseTrailingSlashMatch(false);
		return handlerMapping;
	}

	@Bean
	@Override
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter handlerApapter = super
				.requestMappingHandlerAdapter();
		handlerApapter.setIgnoreDefaultModelOnRedirect(true);
		return handlerApapter;
	}
}