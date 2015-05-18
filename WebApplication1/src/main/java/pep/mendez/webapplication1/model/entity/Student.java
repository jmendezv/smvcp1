package pep.mendez.webapplication1.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Entities are matched to tables in a relational database.
 * 
 * @author pep
 *
 */
@NamedQueries(value = {
		@NamedQuery(name = "Student.findAllStudents", query="FROM Student"),
		@NamedQuery(name = "Student.findStudentById", query = "FROM Student s WHERE s.id = :id"),
		@NamedQuery(name = "Student.findStudentByName", query = "FROM Student s WHERE s.name = :name"),
		@NamedQuery(name = "Student.findStudentByMark", query = "FROM Student s WHERE s.mark BETWEEN :mark1 AND :mark2"),
})
@Entity
@Table(name = "Student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Size(min=5, max=10)
	private String name;
	@Min(value = 0)
	@Max(value = 10)
	private double mark;

	public Student() {
		this.mark = 0.0;
	}
	public Student(long id, String name, double mark) {
		super();
		this.id = id;
		this.name = name;
		this.mark = mark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
