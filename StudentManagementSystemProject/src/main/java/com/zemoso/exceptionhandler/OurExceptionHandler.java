package com.zemoso.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OurExceptionHandler {
    @ExceptionHandler
    public String handleException(StudentNotFoundException exception){
        return "/error/student-not-found";
    }

    @ExceptionHandler
    public String handleException(CourseNotFoundException exception){
        return "/error/course-not-found";
    }

    @ExceptionHandler
    public String handleException(CourseAlreadyExist exception){
        return "error/course-already-exist";
    }
}
