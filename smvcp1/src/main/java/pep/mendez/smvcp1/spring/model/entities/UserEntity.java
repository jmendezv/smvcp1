package pep.mendez.smvcp1.spring.model.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

/*
 * 
 * to some:
 * 
 * Stored Procedures should be considered database assembly language: for use in only the most performance critical situations.
 */
@NamedStoredProcedureQueries({

	@NamedStoredProcedureQuery(
		name = "count_users", 
		procedureName = "count_users", 
		parameters = { 
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = Long.class, name = "total") 
		}) 
})
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

	/**
	 * The serialVersionUID is used as a version control in a Serializable
	 * class. If you do not explicitly declare a serialVersionUID, JVM will do
	 * it for you automatically, based on various aspects of your Serializable
	 * class,
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "username", length = 128, unique = true, nullable = true)
	private String userName;
	@Column(name = "password", length = 255, unique = false, nullable = true)
	private String password;
	@Column(name = "enabled")
	private boolean enabled = false;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
	private Collection<AuthorityEntity> authorities = new LinkedList<AuthorityEntity>();
	// OpenSessionInViewFilter
	// cannot simultaneously fetch multiple bags if EAGER
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<ResetEntity> resets = new LinkedList<ResetEntity>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<ConnectionEntity> connections = new LinkedList<ConnectionEntity>();
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true, mappedBy = "user")
	private ProfileEntity profile;

	public UserEntity() {
	}

	public UserEntity(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public void add(AuthorityEntity authority) {
		if (!authorities.contains(authority))
			authorities.add(authority);
	}

	public void add(ConnectionEntity connection) {
		connections.add(connection);
	}

	public void add(ResetEntity reset) {
		resets.add(reset);
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

	public Collection<AuthorityEntity> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<AuthorityEntity> authorities) {
		this.authorities = authorities;
	}

	public ProfileEntity getProfile() {
		return profile;
	}

	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}

	/**
	 * @return the connections
	 */
	public Collection<ConnectionEntity> getConnections() {
		return connections;
	}

	/**
	 * @param connections
	 *            the connections to set
	 */
	public void setConnections(Collection<ConnectionEntity> connections) {
		this.connections = connections;
	}

	/**
	 * @return the resets
	 */
	public Collection<ResetEntity> getResets() {
		return resets;
	}

	/**
	 * @param resets
	 *            the resets to set
	 */
	public void setResets(Collection<ResetEntity> resets) {
		this.resets = resets;
	}

	@Override
	public String toString() {
		return String.format(
				"User [id=%s, userName=%s, password=%s, enabled=%s]", id,
				userName, password, enabled);
	}

}
