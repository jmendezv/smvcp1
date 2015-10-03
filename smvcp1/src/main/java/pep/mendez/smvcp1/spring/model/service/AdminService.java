package pep.mendez.smvcp1.spring.model.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;

public interface AdminService {

	/*
	 * To use this method users must have ADMIN or ROOT role
	 */
	public abstract void performSomeAdminService1(int id);

	/*
	 * SpEL expressions allowed here. expression must return true to execute
	 */
	public abstract void performSomeAdminService2();

	/*
	 * SpEL expressions allowed here. will always execute but will throw a
	 * security expression if the expression evaluates to false
	 */
	public abstract User performSomeAdminService3(long id);

	/*
	 * Sometimes is not the method that is being secured but rather the data
	 * that being passed into or returned from that method.
	 */
	public abstract void performSomeAdminService4(List<User> users);

	/*
	 * Sometimes is not the method that is being secured but rather the data
	 * that being passed into or returned from that method.
	 */
	public abstract User[] performSomeAdminService5();

	/*
	 * Sometimes is not the method that is being secured but rather the data
	 * that being passed into or returned from that method.
	 * hasPermission will invoke hasPermission method form CustomPermissionEvaluator
	 * hasPermission(<target>, <permission>)
	 */
	public abstract void performSomeAdminService6(List<User> users);

}