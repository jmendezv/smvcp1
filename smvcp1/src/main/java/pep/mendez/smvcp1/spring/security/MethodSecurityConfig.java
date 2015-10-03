package pep.mendez.smvcp1.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/*
 * Method-level security is an important complement to Sring Security's web-level security
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
		CustomPermissionEvaluator customPermissionEvaluator = new CustomPermissionEvaluator();
		// defaults to DenyAllPermissionEvaluator, a null PermissionEvaluator
		// which denies all access. Used by default for situations when
		// permission evaluation should not be required.
		defaultMethodSecurityExpressionHandler
				.setPermissionEvaluator(customPermissionEvaluator);
		return defaultMethodSecurityExpressionHandler;
	}

}
