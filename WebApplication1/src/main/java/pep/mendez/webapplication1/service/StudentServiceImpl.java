package pep.mendez.webapplication1.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pep.mendez.webapplication1.annotations.Auditable;
import pep.mendez.webapplication1.model.dao.StudentDAO;
import pep.mendez.webapplication1.model.entity.Student;

@Service
@Transactional(readOnly = false)
public class StudentServiceImpl implements StudentService {
	
	private StudentDAO studentDAO;

	@Auditable(value = "Student was saved")
	public void save(Student student) {
		studentDAO.save(student);
	}
	
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

}
