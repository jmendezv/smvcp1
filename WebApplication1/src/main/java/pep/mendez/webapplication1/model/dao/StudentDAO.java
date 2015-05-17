package pep.mendez.webapplication1.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pep.mendez.webapplication1.model.entity.Student;

public interface StudentDAO extends JpaRepository<Student, Integer> {
}
