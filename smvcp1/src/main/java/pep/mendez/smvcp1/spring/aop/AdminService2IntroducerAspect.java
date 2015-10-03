package pep.mendez.smvcp1.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import pep.mendez.smvcp1.spring.model.service.AdminService2;
import pep.mendez.smvcp1.spring.model.service.AdminService2Impl;

/*
 * This aspect introduces AdminService2 interface to AdminService.
 * 
 * Java isn't that dynamic, once a class has been compiled, there's little you can do to append new functionality to it.
 * 
 * With aspects you don't add new methods to objects, you simply add new functionality around the methods that the objects already have.
 * 
 * Using an AOP concept known as introduction, aspects can attach new methods to Spring beans.
 * 
 * In Spring, aspects are proxies that implement the same interfaces as the beans they wrap.
 * 
 * What if, in addition to implementing those interfaces, the proxy is also exposed through some new interface?
 * 
 * Then any bean that is advised by the aspect will appear to implement the new interface, even if its underlying implementation class doesn't.
 * 
 * Notice that when a method on the introduced interface is called, the proxy delegates the call to some other object that provides the implementation of the new interface.
 * 
 * Effectively, this gives you one bean whose implementation is split across multiple classes.
 * 
 */
@Aspect
public class AdminService2IntroducerAspect {

	@DeclareParents(value = "pep.mendez.smvcp1.spring.service.AdminService+", defaultImpl = AdminService2Impl.class)
	/*
	 * The @DeclareParents annotation is made up of three parts:
	 * 
	 * 1.- value - AdminService2 is being introduced to any implementation of
	 * AdminService beans.
	 * 
	 * 2.- defaultImpl - identifies the class that will provide the
	 * implementation for the introduction: AdminService2Impl
	 * 
	 * 3.- The public static property specifies the interface that's to be
	 * introduced: AdminService2
	 */
	public static AdminService2 adminService2;

}
