package com.zemoso.service;

import com.zemoso.dao.CourseRepository;
import com.zemoso.entity.Course;
import com.zemoso.exceptionhandler.CourseAlreadyExist;
import com.zemoso.exceptionhandler.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(int theId) {
        Optional<Course> result = courseRepository.findById(theId);
        if (!result.isPresent())
            throw new CourseNotFoundException("INVALID COURSE ID " + theId);
        return result.get();
    }

    @Override
    public void save(Course course) {
        List<Course> courses=courseRepository.findAll();
        List<String> titles=new ArrayList<>();
        for(Course course1:courses)
            titles.add(course1.getTitle().toUpperCase());

        if(titles.contains(course.getTitle().toUpperCase()))
            throw new CourseAlreadyExist("course already exist");
        else
            courseRepository.save(course);

    }

    @Override
    public void deleteById(int theId) {
        Optional<Course> tempCourse=courseRepository.findById(theId);
        if(!(tempCourse.isPresent())){
            throw new CourseNotFoundException("INVALID COURSE ID -"+theId);
        }
        courseRepository.deleteById(theId);
    }
}
