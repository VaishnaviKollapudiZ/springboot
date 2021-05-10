package com.zemoso.exceptionhandler;

public class CourseAlreadyExist extends RuntimeException {
    public CourseAlreadyExist(String message) {
        super(message);
    }

}
