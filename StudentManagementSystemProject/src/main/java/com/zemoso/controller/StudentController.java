package com.zemoso.controller;

import java.util.List;

import com.zemoso.entity.Course;
import com.zemoso.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zemoso.entity.Student;
import com.zemoso.service.StudentService;


@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@GetMapping("/list")
	public String listStudents(Model theModel) {

		List<Student> theStudents = studentService.findAll();
		theModel.addAttribute("students", theStudents);
		
		return "students/list-students";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		List<Course> courses=courseService.findAll();

		Student theStudent = new Student();
		
		theModel.addAttribute("student", theStudent);
		theModel.addAttribute("courses",courses);

		return "students/student-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId,
									Model theModel) {

		List<Course> courses=courseService.findAll();

		Student theStudent = studentService.findById(theId);

		theModel.addAttribute("student", theStudent);

		theModel.addAttribute("courses",courses);
		
		return "students/student-form";
	}
	
	
	@PostMapping("/save")
	public String saveStudent(@Validated @ModelAttribute("student") Student theStudent, BindingResult bindingResult,Model model){
		if(bindingResult.hasErrors())
		{
			List<Course> courses=courseService.findAll();
			model.addAttribute("courses",courses);
			return "students/student-form";
		}
		else {
			studentService.save(theStudent);
			return "redirect:/students/list";
		}
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		studentService.deleteById(theId);
		return "redirect:/students/list";
		
	}
}


















