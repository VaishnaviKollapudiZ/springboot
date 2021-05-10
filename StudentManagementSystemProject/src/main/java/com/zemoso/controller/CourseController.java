package com.zemoso.controller;

import com.zemoso.entity.Course;
import com.zemoso.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping("/list")
    public String listCourses(Model theModel) {

        List<Course> courses = courseService.findAll();

        theModel.addAttribute("courses", courses);

        return "courses/list-courses";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

       Course course=new Course();
        theModel.addAttribute("course",course);

        return "courses/course-form";
    }

    @PostMapping("/save")
    public String saveCourse(@Validated @ModelAttribute("course") Course course, BindingResult bindingResult){

        if(bindingResult.hasErrors())
        {
            return "courses/course-form";
        }
        else {
            courseService.save(course);

            return "redirect:/courses/list";
        }
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("courseId") int theId) {
        courseService.deleteById(theId);
        return "redirect:/courses/list";
    }

}
