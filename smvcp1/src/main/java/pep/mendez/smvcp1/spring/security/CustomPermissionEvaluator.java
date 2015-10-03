package pep.mendez.smvcp1.spring.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;

/*
 * Strategy used in expression evaluation to determine whether a user has a permission or permissions for a given domain object.
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {
	
	private static final GrantedAuthority ADMIN_AUTHORITY = new GrantedAuthorityImpl("ROLE_ADMIN");

	/**
	 * authentication - represents the user in question. Should not be null.
	 * 
	 * targetDomainObject - the domain object for which permissions should be
	 * checked. May be null in which case implementations should return false,
	 * as the null condition can be checked explicitly in the expession.
	 * 
	 * permission - a representation of the permission object as supplied by the
	 * expression system. Not null.
	 */
	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		// TODO Providec as an example
		if (targetDomainObject == null) {
			return false;
		}
		if(targetDomainObject instanceof User) {
			User user = (User) targetDomainObject;
			String username = user.getPassword();
			if("action".equals(permission)) {
				return isAdmin(authentication) || username.equals(authentication.getName());
			}
		}
		return false;
	}

	private boolean isAdmin(Authentication authentication) {
		return authentication.getAuthorities().contains(ADMIN_AUTHORITY);
	}

	/**
	 * authentication - represents the user in question. Should not be null.
	 * 
	 * targetId - the identifier for the object instance (usually a Long)
	 * 
	 * targetType - a String representing the target's type (usually a Java
	 * classname). Not null.
	 * 
	 * permission - a representation of the permission object as supplied by the
	 * expression system. Not null.
	 */
	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
