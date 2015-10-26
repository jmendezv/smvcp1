package pep.mendez.smvcp1.spring.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "resets")
public class ResetEntity extends BaseEntity implements Comparable<ResetEntity> {

	/**
	 * The serialVersionUID is used as a version control in a Serializable
	 * class. If you do not explicitly declare a serialVersionUID, JVM will do
	 * it for you automatically, based on various aspects of your Serializable
	 * class,
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="resetCode")
	private long resetCode;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateExpiry")
	private Date dateExpiry;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private UserEntity user;

	public ResetEntity() {
	}

	public ResetEntity(long resetCode, Date dateExpriy) {
		this.resetCode = resetCode;
		this.dateExpiry = dateExpriy;
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
	
	/**
	 * @return the resetCode
	 */
	public long getResetCode() {
		return resetCode;
	}

	/**
	 * @param resetCode the resetCode to set
	 */
	public void setResetCode(long resetCode) {
		this.resetCode = resetCode;
	}
	/**
	 * @return the dateEspiry
	 */
	public Date getDateExpiry() {
		return dateExpiry;
	}

	/**
	 * @param dateEspiry the dateEspiry to set
	 */
	public void setDateExpiry(Date dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	@Override
	public int compareTo(ResetEntity reset) {
		// TODO Auto-generated method stub
		return id < reset.getId() ? -1 : id == 0 ? 0 : 1;
	}
}
