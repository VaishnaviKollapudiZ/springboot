package com.zemoso.service;

import java.util.List;
import java.util.Optional;
import com.zemoso.dao.StudentRepository;
import com.zemoso.entity.Student;
import com.zemoso.exceptionhandler.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;


	@Override
	public List<Student> findAll() {

		return studentRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Student findById(int theId) {
		Optional<Student> result = studentRepository.findById(theId);
		Student theStudent;
		
		if (result.isPresent()) {
			theStudent = result.get();
		}
		else {
			throw new StudentNotFoundException("Did not find student id - " + theId);
		}
		return theStudent;
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void deleteById(int theId) {
		Optional<Student> tempStudent=studentRepository.findById(theId);
		if(!(tempStudent.isPresent())){
			throw new StudentNotFoundException("Did not find student id -"+theId);
		}
		studentRepository.deleteById(theId);


	}
}






