package pep.mendez.smvcp1.spring.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*
 * AbstractSecurityWebApplicationInitializer implements WebApplicationInitializer
 * so it will be discovered by Spring and used to register
 * the DelegatingFilterProxy to use the springSecurityFilterChain 
 * before any other registered Filter. 
 * 
 * It will Intercept requests coming into the application and delegate them
 * to a bean whose ID is springSecurityFilterChain (a FilterChainProxy)
 * 
 * Spring Security relies on several servlet filters to provide different
 * security features. Those filters will be created when you enable web security
 */
public class SecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {

}
