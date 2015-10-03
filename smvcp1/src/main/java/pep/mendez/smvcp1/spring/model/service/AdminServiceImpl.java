package pep.mendez.smvcp1.spring.model.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/* Spring automatically scans and identifies all these classes that are annotated with 
 * @Component, @Service, @Repository, @Controller  and registers BeanDefinition with ApplicationContext 
 */
@Service
public class AdminServiceImpl implements AdminService {

	/*
	 * To use this method users must have ADMIN or ROOT role
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pep.mendez.smvcp1.spring.service.AdminService#performSomeAdminService1
	 * (int)
	 */
	@Override
	@Secured({ "ROLE_ADMIN", "ROLE_ROOT" })
	public void performSomeAdminService1(int id) {
		System.out.println("in perfomSomeAdminService1");
	}

	/*
	 * SpEL expressions allowed here. expression must return true to execute
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pep.mendez.smvcp1.spring.service.AdminService#performSomeAdminService2()
	 */
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void performSomeAdminService2() {
		System.out.println("in perfomSomeAdminService2");
	}

	/*
	 * SpEL expressions allowed here. will always execute but will throw a
	 * security expression if the expression evaluates to false
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pep.mendez.smvcp1.spring.service.AdminService#performSomeAdminService3
	 * (long)
	 */
	@Override
	@PostAuthorize("returnObject.user.username == principal.username")
	public User performSomeAdminService3(long id) {
		System.out.println("in perfomSomeAdminService3");
		return null;
	}

	/*
	 * Sometimes is not the method that is being secured but rather the data
	 * that being passed into or returned from that method.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pep.mendez.smvcp1.spring.service.AdminService#performSomeAdminService4
	 * (java.util.List)
	 */
	@Override
	@PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
	@PreFilter("hasRole('ROLE_ADMIN') || targetObject.user.username == principal.name")
	public void performSomeAdminService4(List<User> users) {
		System.out.println("in perfomSomeAdminService4");
	}

	/*
	 * Sometimes is not the method that is being secured but rather the data
	 * that being passed into or returned from that method.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pep.mendez.smvcp1.spring.service.AdminService#performSomeAdminService5()
	 */
	@Override
	@PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
	@PostFilter("hasRole('ROLE_ADMIN') || filterObject.user.username == principal.username")
	public User[] performSomeAdminService5() {
		System.out.println("in perfomSomeAdminService5");
		return null;
	}

	/*
	 * Sometimes is not the method that is being secured but rather the data
	 * that being passed into or returned from that method. hasPermission will
	 * invoke hasPermission method form CustomPermissionEvaluator
	 * hasPermission(<target>, <permission>)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pep.mendez.smvcp1.spring.service.AdminService#performSomeAdminService6
	 * (java.util.List)
	 */
	@Override
	@PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
	@PreFilter("hasPermision(targetObject, 'action')")
	public void performSomeAdminService6(List<User> users) {
		System.out.println("in perfomSomeAdminService6");
	}
}
