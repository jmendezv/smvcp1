package pep.mendez.smvcp1.spring.model.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*
 * Designates a class whose mapping information is applied to the entities that inherit from it. 
 * 
 * A mapped superclass has no separate table defined for it.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isNew() {
		return (this.id == null);
	}

}