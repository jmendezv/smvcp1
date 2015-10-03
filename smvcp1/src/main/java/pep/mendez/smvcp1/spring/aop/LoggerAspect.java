package pep.mendez.smvcp1.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public void beforeAllPublicServiceIntMethodsPointcutAdvice(JoinPoint joinPoint, int id) {
		logger.info(String.format("*** %s (%d) ***%n", joinPoint.getSignature(), id));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
