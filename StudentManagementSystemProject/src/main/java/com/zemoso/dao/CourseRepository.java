package com.zemoso.dao;

import com.zemoso.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CourseRepository extends JpaRepository<Course,Integer> {
}
