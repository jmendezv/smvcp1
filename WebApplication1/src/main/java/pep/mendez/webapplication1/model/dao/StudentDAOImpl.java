package pep.mendez.webapplication1.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import pep.mendez.webapplication1.model.entity.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {
	
	// Looks for an entityManagerFactory bean
	@PersistenceContext
	private EntityManager entityManager;

	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	public void deleteInBatch(Iterable<Student> students) {
		// TODO Auto-generated method stub

	}

	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Student> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Student> findAll(Iterable<Integer> students) {
		// TODO Auto-generated method stub
		return null;
	}

	public void flush() {
		// TODO Auto-generated method stub

	}

	public Student getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Student> List<S> save(Iterable<S> students) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Student> S saveAndFlush(S student) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Student> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void delete(Integer arg0) {
		// TODO Auto-generated method stub

	}

	public void delete(Student student) {
		// TODO Auto-generated method stub

	}

	public void delete(Iterable<? extends Student> students) {
		// TODO Auto-generated method stub

	}

	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Student findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Student> S save(S student) {
		entityManager.persist(student);
		return student;
	}

}
