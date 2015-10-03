package pep.mendez.smvcp1.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import pep.mendez.smvcp1.spring.SpringDef;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { SpringDef.class })
public class DispatcherServletConfig extends WebMvcConfigurerAdapter {

	// @Override
	// public void configureContentNegotiation(
	// ContentNegotiationConfigurer configurer) {
	// configurer.defaultContentType(MediaType.APPLICATION_JSON);
	// }

	// @Override
	// public void configureMessageConverters(List<HttpMessageConverter<?>>
	// converters) {
	// // http
	// // HttpMessageConverter converter = new StringHttpMessageConverter();
	// // converters.add(converter);
	//
	// // string
	// // converter = new FormHttpMessageConverter();
	// // converters.add(converter);
	//
	// // json
	// converters.add(new MappingJackson2HttpMessageConverter());
	//
	// }

	/**
	 * Access resource files
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars");
	}

	/**
	 * Configure static content handling
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
