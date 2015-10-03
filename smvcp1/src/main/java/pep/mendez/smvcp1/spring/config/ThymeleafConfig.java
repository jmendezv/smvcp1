package pep.mendez.smvcp1.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Configure a Thymeleaf view resolver
 * 
 * lower order value has a higher priority
 * 
 * @return ViewResolver
 */
@Configuration
@PropertySource("classpath:thymeleaf.properties")
public class ThymeleafConfig {

	@Bean
	public TemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/thymeleaf/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		//templateResolver.setOrder(0);
		templateResolver.setCacheable(false);

		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(springSecurityDialect());
		return templateEngine;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		// vr.setViewClass(ThymeleafTilesView.class);
		// vr.setCharacterEncoding("UTF-8");		
		//viewResolver.setViewNames(new String[] { "*.html" });
		viewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return viewResolver;
	}
	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		SpringSecurityDialect springSecurityDialect = new SpringSecurityDialect();
		return springSecurityDialect;
	}
	
	
}