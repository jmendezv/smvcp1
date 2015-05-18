package pep.mendez.webapplication1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pep.mendez.webapplication1.annotations.Auditable;
import pep.mendez.webapplication1.model.entity.Student;

/**
 * Aspects enable the modularization of cross cutting concerns such as
 * transaction management that cut across multiple types and objects.
 * 
 * They are made up of pointcuts, execution methods we want to monitor, and
 * advises, actions to take before, after or before and after (around).
 * 
 * @author pep
 *
 */
@Component
@Aspect
public class LoggerAspect {

	private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

	@Pointcut(value = "execution (public * pep.mendez.webapplication1.service.*.*(..))")
	public void allPublicServiceMethodsPointcut() {
	}

	// @Pointcut("allPublicServiceMethodsPointcut() && @annotation(auditable)")
	// public void allAuditablePublicServiceMethodsPointcut(Auditable auditable)
	// {}

	// @Pointcut("allPublicServiceMethodsPointcut() && args(student, ..)")
	// public void studentArgsPublicServiceMethodsPointcut(Student student) {}

	@Before("allPublicServiceMethodsPointcut()")
	public void beforeAllPublicServiceMethodsPointcutAdvice(JoinPoint joinPoint) {
		logger.info(String.format("%s%n", joinPoint.getSignature()));
	}

	// @Before("allAuditablePublicServiceMethodsPointcut(auditable)")
	// public void beforeStudentArgsPublicServiceMethodsAdvice(JoinPoint
	// joinPoint, Auditable auditable) {
	// logger.info(String.format("%s%n", joinPoint.getSignature()));
	// }

	// @Before("allAuditablePublicServiceMethodsPointcut(student)")
	// public void beforeStudentArgsPublicServiceMethodsAdvice(JoinPoint
	// joinPoint, Student student) {
	// logger.info(String.format("%s %s%n", student.toString(),
	// joinPoint.getSignature()));
	// }

}
