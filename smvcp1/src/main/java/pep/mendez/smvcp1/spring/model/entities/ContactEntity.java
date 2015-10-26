package pep.mendez.smvcp1.spring.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "contacts")
public class ContactEntity extends BaseEntity {

	/**
	 * The serialVersionUID is used as a version control in a Serializable
	 * class. If you do not explicitly declare a serialVersionUID, JVM will do
	 * it for you automatically, based on various aspects of your Serializable
	 * class,
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "username", length = 128, unique = true, nullable = false)
	private String userName;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datecontact")
	private Date dateContact;
	@Column(name = "subject", length = 255)
	private String subject;
	@Column(name = "comments")
	private String comments;

	public ContactEntity() {
	}

	public ContactEntity(String userName, Date dateContact, String subject,
			String comments) {

		this.dateContact = dateContact;
		this.userName = userName;
		this.subject = subject;
		this.comments = comments;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the dateContact
	 */
	public Date getDateContact() {
		return dateContact;
	}

	/**
	 * @param dateContact
	 *            the dateContact to set
	 */
	public void setDateContact(Date dateContact) {
		this.dateContact = dateContact;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("Contact [id=%s, userName=%s, dateContact=%s, subject=%s, comments=%s]",
						id, userName, dateContact, subject, comments);
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
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (!(obj instanceof ContactEntity)) {
			return false;
		}
		ContactEntity other = (ContactEntity) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
