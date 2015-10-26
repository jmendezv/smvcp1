package pep.mendez.smvcp1.spring.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author pep
 *
 */
@Entity
@Table(name = "authorities")
public class AuthorityEntity extends BaseEntity {

	
	/**
	 * The serialVersionUID is used as a version control in a Serializable
	 * class. If you do not explicitly declare a serialVersionUID, JVM will do
	 * it for you automatically, based on various aspects of your Serializable
	 * class,
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "username", length = 64, nullable = false, unique = false)
	private String userName;
	@Column(name = "authority", length = 64, nullable = false, unique = false)
	private String authority;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = true)
	private UserEntity user;

	public AuthorityEntity() {
	}

	public AuthorityEntity(String userName, String authority) {
		this.userName = userName;
		this.authority = authority;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		if (!(obj instanceof AuthorityEntity)) {
			return false;
		}
		AuthorityEntity other = (AuthorityEntity) obj;
		if (authority == null) {
			if (other.authority != null) {
				return false;
			}
		} else if (!authority.equals(other.authority)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"Authority [id=%s, userName=%s, authority=%s, user=%s]", id,
				userName, authority, user);
	}
	
}
