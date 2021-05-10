package com.zemoso.service;

import com.zemoso.entity.Course;

import java.util.List;

public interface CourseService {
    public List<Course> findAll();

    public Course findById(int theId);

    public void save(Course course);

    public void deleteById(int theId);
}
