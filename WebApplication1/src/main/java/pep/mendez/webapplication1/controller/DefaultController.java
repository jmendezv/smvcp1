package pep.mendez.webapplication1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pep.mendez.webapplication1.model.entity.Student;
import pep.mendez.webapplication1.service.StudentService;

/**
 * The Controler from the MVC model
 * 
 * @author pep
 *
 */
@Controller
@RequestMapping("/")
public class DefaultController {

	@Autowired(required = true)
	private StudentService studentService;

	@RequestMapping(value = "/showMessage.html")
	public ModelAndView showMessage() {
		ModelAndView mav = new ModelAndView("showMessage", "student",
				new Student());
		return mav;
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public ModelAndView formPost(@Valid @ModelAttribute Student student,
			BindingResult results) {
		ModelAndView mav = null;
		if (results.hasErrors()) {
			mav = new ModelAndView("showMessage", "student", student);
		} else {
			mav = new ModelAndView("showMessage", "student", new Student());
			mav.addObject("student1", student);
			studentService.save(student);
		}
		return mav;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
}
