package pep.mendez.smvcp1.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pep
 * 
 *         Aspect-Oriented Programming (AOP) complements Object-Oriented
 *         Programming (OOP) by providing another way of thinking about program
 *         structure.
 * 
 *         The key unit of modularity in OOP is the class, whereas in AOP the
 *         unit of modularity is the aspect.
 * 
 *         Aspects enable the modularization of concerns (cross-cutting
 *         concerns) such as transaction management or logging that cut across
 *         multiple types of objects.
 * 
 *         One aspect represents the modularization of a concern that cuts
 *         across multiples classes.
 * 
 *         this class register calls to the service layer.
 */
@Aspect
public class LoggerAspect {

	/*
	 * Pointcuts define which join points are advised, can be method execution,
	 * exceptions thrown or objects being changed. Pointcuts define the where.
	 * 
	 * Advice is the action you will take. Advices define the what.
	 */

	private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

	@Pointcut(value = "execution (** pep.mendez.smvcp1.spring.service.*.*(..))")
	public void allPublicServiceMethodsPointcut() {
	}

	@Pointcut(value = "execution (* pep.mendez.smvcp1.spring.service.*.*(int)) && args(id)")
	public void allPublicServiceIntMethodsPointcut(int id) {
	}

	@Pointcut("allPublicServiceMethodsPointcut() && @annotation(auditable)")
	public void allPublicAuditableServiceMethodsPointcut(Auditable auditable) {
	}

	@Before("allPublicServiceIntMethodsPointcut(id)")
	public void beforeAllPublicServiceIntMethodsPointcutAdvice(
			JoinPoint joinPoint, int id) {
		logger.info(String.format("*** %s (%d) ***%n",
				joinPoint.getSignature(), id));
	}

	@Before("allPublicServiceMethodsPointcut()")
	public void beforeAllPublicServiceMethodsPointcutAdvice(JoinPoint joinPoint) {
		logger.info(String.format("*** %s ***%n", joinPoint.getSignature()));
	}

	@Around("allPublicServiceMethodsPointcut()")
	public void aroundAllPublicServiceMethodsPointcutAdvice(
			ProceedingJoinPoint procidingJoinPoint) {

		try {
			logger.info(String.format("*** %s ***%n",
					procidingJoinPoint.getSignature()));
			procidingJoinPoint.proceed();
			// whatever more you want to do
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
