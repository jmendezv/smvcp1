package pep.mendez.smvcp1.spring.formbeans;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class ContactBean implements Comparable<ContactBean>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank
	@Email
	private String userName;
	private Date dateContact = new Date();
	@NotBlank
	private String subject;
	@NotBlank
	private String comments;

	public ContactBean() {
	}

	public ContactBean(String userName, Date dateContact, String subject,
			String comments) {
		this.userName = userName;
		this.dateContact = dateContact;
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

	@Override
	public int compareTo(ContactBean contactBean) {
		return this.dateContact.compareTo(contactBean.getDateContact());
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
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result
				+ ((dateContact == null) ? 0 : dateContact.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		if (!(obj instanceof ContactBean)) {
			return false;
		}
		ContactBean other = (ContactBean) obj;
		if (comments == null) {
			if (other.comments != null) {
				return false;
			}
		} else if (!comments.equals(other.comments)) {
			return false;
		}
		if (dateContact == null) {
			if (other.dateContact != null) {
				return false;
			}
		} else if (!dateContact.equals(other.dateContact)) {
			return false;
		}
		if (subject == null) {
			if (other.subject != null) {
				return false;
			}
		} else if (!subject.equals(other.subject)) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("ContactBean [userName=%s, dateContact=%s, subject=%s, comments=%s]",
						userName, dateContact, subject, comments);
	}

}
