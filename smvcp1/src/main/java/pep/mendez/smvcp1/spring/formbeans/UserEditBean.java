package pep.mendez.smvcp1.spring.formbeans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pep
 *
 */
public class UserEditBean {

	private String userName;
	private boolean enabled;
	private List<String> roles;

	public UserEditBean() {
		this(null, false);
	}

	public UserEditBean(String name, boolean enabled) {
		this(name, enabled, new ArrayList<String>());
	}

	public UserEditBean(String name, boolean enabled, List<String> roles) {
		this.userName = name;
		this.enabled = enabled;
		this.roles = roles;
	}

	public void addRole(String role) {
		roles.add(role);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserEditBean)) {
			return false;
		}
		UserEditBean other = (UserEditBean) obj;
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("UserEditBean [name=%s, enabled=%s, roles=%s]",
				userName, enabled, roles);
	}
}
