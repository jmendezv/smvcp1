package pep.mendez.smvcp1.spring.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author pep
 *
 */
@Entity
@Table(name = "profiles")
public class ProfileEntity extends BaseEntity {

	/**
	 * The serialVersionUID is used as a version control in a Serializable
	 * class. If you do not explicitly declare a serialVersionUID, JVM will do
	 * it for you automatically, based on various aspects of your Serializable
	 * class,
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name", length = 32, nullable = true, unique = false)
	private String name;
	@Column(name = "city", length = 32, nullable = true, unique = false)
	private String city;
	@Column(name = "profession", length = 32, nullable = true, unique = false)
	private String profession;
	@Column(name = "phone", length = 16, nullable = true, unique = false)
	private String phone;
	@Column(name = "ip", length = 64, nullable = true, unique = false)
	private String ip;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = true, unique = false)
	private Date date;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = true)
	private UserEntity user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
