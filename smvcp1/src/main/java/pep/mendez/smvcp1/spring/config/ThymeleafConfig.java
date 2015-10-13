package pep.mendez.smvcp1.spring.config;

import net.sourceforge.pagesdialect.PagesDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;

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
import org.thymeleaf.templateresolver.UrlTemplateResolver;

/**
 * Configure a Thymeleaf
 * 
 * lower order value has a higher priority
 * 
 * @return ViewResolver
 */
/**
 * @author pep
 *
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
		// templateResolver.setOrder(0);
		templateResolver.setCacheable(false);

		return templateResolver;
	}

	/*
	 * Thymeleaf can select an arbitrary section of a page as a fragment (even a
	 * page living on an external server) by means of its DOM Selector syntax,
	 * similar to XPath expressions and CSS selectors.
	 * 
	 * <div th:include="http://www.thymeleaf.org :: p.notice" >...</div>
	 * 
	 * The above code will include a paragraph with class="notice" from
	 * thymeleaf.org. In order to make it happen, the template engine must be
	 * configured with UrlTemplateResolver.
	 * 
	 */
	@Bean
	public UrlTemplateResolver urlTemplateResolver() {
		return new UrlTemplateResolver();
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		// unsupportedexception
		//templateEngine.addTemplateResolver(new UrlTemplateResolver());
		// throws exception
		//templateEngine.addDialect(new StandardDialect());
		templateEngine.addDialect(new SpringSecurityDialect());
		templateEngine.addDialect(new PagesDialect());
		templateEngine.addDialect(new LayoutDialect());
		//templateEngine.addDialect(new TilesDialect());
		
		return templateEngine;
	}
	
//	@Bean
//	public ViewResolver tilesViewResolver() {
//	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//	    viewResolver.setTemplateEngine(templateEngine());
//	    viewResolver.setViewClass(ThymeleafTilesView.class);
//	    viewResolver.setCharacterEncoding("UTF-8");
//	    viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE);
//	    return viewResolver;
//	}
	
	@Bean
	public ViewResolver thymeleafViewResolver() {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    viewResolver.setCharacterEncoding("UTF-8");
	    viewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    // all message/* views will not be handled by this resolver;
	    //viewResolver.setExcludedViewNames(new String[]{"message/*"});
	    return viewResolver;
	}
	
//	@Bean
//	public ThymeleafTilesConfigurer tilesConfigurer() {
//	    ThymeleafTilesConfigurer tilesConfigurer = new ThymeleafTilesConfigurer();
//	    tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/views/message/tiles-defs.xml"});
//	    return tilesConfigurer;
//	}

}