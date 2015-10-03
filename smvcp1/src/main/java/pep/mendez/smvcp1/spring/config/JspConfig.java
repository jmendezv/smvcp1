package pep.mendez.smvcp1.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configure a JSP view resolver
 * 
 * lower order value has a higher priority
 * 
 * The InternalResourceViewResolver must always assign with the lowest priority
 * (largest order number), because it will resolve the view no matter what view
 * name is returned.
 * 
 * @return ViewResolver
 */
@Configuration
public class JspConfig {

//	@Bean
//	public ViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setExposeContextBeansAsAttributes(true);
//		return viewResolver;
//	}

}
