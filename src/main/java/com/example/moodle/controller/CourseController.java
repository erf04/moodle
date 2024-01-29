package com.example.moodle.controller;

import com.example.moodle.model.Account;
import com.example.moodle.model.Admin;
import com.example.moodle.model.Course;
import com.example.moodle.repository.CourseRepository;
import com.example.moodle.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CourseController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CourseRepository courseRepository;
    @GetMapping("/addcourse/{user_id}")
    public String showAddCourse(@PathVariable Long user_id, Model model) {
        Account user=accountService.findByID(user_id);
        if(user instanceof Admin){
            Course course=new Course();
            model.addAttribute("course",course);
            model.addAttribute("user",accountService.findByID(user_id));
            return "addCourse";
        }
        else return null;

    }
    @PostMapping("/saveCourse/{user_id}")
    public  String courseSave(@ModelAttribute("course") Course course,@PathVariable("user_id") Long id){
        System.out.println(course.getName());
        courseRepository.save(course);
        return "redirect:/addcourse/"+id;
    }

    @PostMapping("/searchCourse")
    public  String courseSearch(@RequestParam("searchedContent") String partialCourseName){
        System.out.println(partialCourseName);
        //courseRepository.searchCourse();
        return "searched";
    }
}
