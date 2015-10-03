package pep.mendez.smvcp1.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Configure a Tiles view resolver
 * 
 * lower order value has a higher priority
 * 
 * @return ViewResolver
 */
@Configuration
public class TilesConfig {

//	@Bean
//	public TilesConfigurer tilesConfigurer() {
//		TilesConfigurer tiles = new TilesConfigurer();
//		tiles.setDefinitions("/WEB-INF/layout/tiles.xml");
//		return tiles;
//	}

//	@Bean
//	public ViewResolver viewResolver() {
//		TilesViewResolver tilesViewResolver = new TilesViewResolver();
//		tilesViewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return tilesViewResolver;
//		// UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
//		// viewResolver.setViewClass(TilesViewResolver.class);
//		// return viewResolver;
//	}
}
