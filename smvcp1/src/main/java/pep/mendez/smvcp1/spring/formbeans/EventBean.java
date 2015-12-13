package pep.mendez.smvcp1.spring.formbeans;

import java.io.Serializable;
import java.util.Date;

public class EventBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String resourceId;
	private String title;
	private Date start;
	private Date end;

	public EventBean() {

	}

	public EventBean(long id, String resourceId, String title, Date start,
			Date end) {
		this.id = id;
		this.resourceId = resourceId;
		this.title = title;
		this.start = start;
		this.end = end;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EventBean [id=" + id + ", resourceId=" + resourceId
				+ ", title=" + title + ", start=" + start + ", end=" + end
				+ "]";
	}

}
