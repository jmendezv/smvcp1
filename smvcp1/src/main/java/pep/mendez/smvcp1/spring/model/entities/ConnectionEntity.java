package pep.mendez.smvcp1.spring.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author pep
 *
 */
@Entity
@Table(name = "connections")
public class ConnectionEntity extends BaseEntity {

	/**
	 * The serialVersionUID is used as a version control in a Serializable
	 * class. If you do not explicitly declare a serialVersionUID, JVM will do
	 * it for you automatically, based on various aspects of your Serializable
	 * class,
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	// @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date timeIn;
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date timeOut;
	// Ready for IPv6
	@Column(name = "ip", length = 64)
	private String ip;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private UserEntity user;

	public ConnectionEntity() {
	}

	public ConnectionEntity(Date timeIn, String ip) {
		this.timeIn = timeIn;
		this.ip = ip;
	}

	public Date getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the userConnection
	 */
	public UserEntity getUser() {
		return user;
	}

	/**
	 * @param userConnection
	 *            the userConnection to set
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}
}
