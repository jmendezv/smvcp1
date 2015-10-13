package pep.mendez.smvcp1.spring.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		int startTime = Integer.parseInt(env.getProperty("startTime"));
		Integer endTime = Integer.valueOf(env.getProperty("endTime"));
		CustomScheduleInterceptor customScheduleInterceptor = new CustomScheduleInterceptor(startTime, endTime);
		registry.addInterceptor(customScheduleInterceptor).addPathPatterns("/login"); 
	}
}