package pep.mendez.smvcp1.spring.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "events")
/*
 * create table events ( id bigint primary key auto_increment, title
 * varchar(64), start datetime, end datetime);
 */
public class EventEntity extends BaseEntity {
	
	/**
	 * The serialVersionUID is used as a version control in a Serializable
	 * class. If you do not explicitly declare a serialVersionUID, JVM will do
	 * it for you automatically, based on various aspects of your Serializable
	 * class,
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "resourceId", length = 32, unique = false, nullable = false)
	private String resourceId;
	@Column(name = "title", length = 64, unique = false, nullable = false)
	private String title;
	// mapea a datetime
	@Temporal(TemporalType.TIMESTAMP)
	// @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date start;
	@Temporal(TemporalType.TIMESTAMP)
	// @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date end;

	public EventEntity() {
		// TODO Auto-generated constructor stub
	}

	public EventEntity(String resourceId, String title, Date start, Date end) {
		this.resourceId = resourceId;
		this.title = title;
		this.start = start;
		this.end = end;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}
