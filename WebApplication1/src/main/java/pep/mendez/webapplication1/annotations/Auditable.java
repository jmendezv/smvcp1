package pep.mendez.webapplication1.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Pointcuts can use this annotation to monitor Auditable annotated methods
 * 
 * @author pep
 *
 */
/* This annotation will be present in javadoc */
@Documented
/* This annotation will retain info at runtime level */
@Retention(RetentionPolicy.RUNTIME)
/* This annotation will be available only to methods */
@Target(value = { ElementType.METHOD })
public @interface Auditable {
	String value();
}
