package pep.mendez.smvcp1.spring.model.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * OrphanRemoval tells the ORM that if I remove an Item object from the
 * collection of Items that belong to an Invoice object (in memory operation),
 * and then "save" the Invoice, the removed Item should be deleted from the
 * underlying DB
 * 
 * @author pep
 * 
 *         If you try multiple eager collections fetch you will get:
 * 
 *         cannot simultaneously fetch multiple bags
 *
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * The serialVersionUID is used as a version control in a Serializable
	 * class. If you do not explicitly declare a serialVersionUID, JVM will do
	 * it for you automatically, based on various aspects of your Serializable
	 * class,
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "username", length = 128, unique = true, nullable = false)
	private String userName;
	@Column(name = "password", length = 255, unique = false, nullable = false)
	private String password;
	@Column(name = "enabled")
	private boolean enabled = false;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
	private Collection<Authority> authorities = new LinkedList<Authority>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	private Collection<Connection> connections = new LinkedList<Connection>();
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true, mappedBy = "user")
	private Profile profile;

	public User() {
	}

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public void add(Authority authority) {
		if (!authorities.contains(authority))
			authorities.add(authority);
	}

	public void add(Connection connection) {
		connections.add(connection);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return String.format(
				"User [id=%s, userName=%s, password=%s, enabled=%s]", id,
				userName, password, enabled);
	}


}
