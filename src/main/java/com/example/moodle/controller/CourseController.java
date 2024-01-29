package com.example.moodle.controller;

import com.example.moodle.model.Account;
import com.example.moodle.model.Admin;
import com.example.moodle.model.Course;


import com.example.moodle.model.*;
import com.example.moodle.repository.CoursePlanRepository;
import com.example.moodle.repository.CourseRepository;
import com.example.moodle.service.AccountService;
import com.example.moodle.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


@Controller
public class CourseController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CoursePlanRepository coursePlanRepository;

    @GetMapping("/addcourse/{user_id}")
    public String showAddCourse(@PathVariable Long user_id, Model model) {
        Account user=accountService.findByID(user_id);
        model.addAttribute("user",user);
        if(user instanceof Admin){
            Course course=new Course();
            model.addAttribute("course",course);

            return "addCourse";
        }
        else{
            return "error500";
        }


    }
    @PostMapping("/saveCourse/{user_id}")
    public  String courseSave(@ModelAttribute("course") Course course,@PathVariable("user_id") Long id){
        System.out.println(course.getName());
        Account teacher= teacherService.findTeacherById(id);
        if (teacher!=null){
            courseRepository.save(course);
            return "redirect:/addcourse/"+id;
        }
        else{
            return null;
        }

    }
    @GetMapping("/addcourseplan/{user_id}")
    public String showAddCoursePlan(@PathVariable("user_id") long user_id,Model model){
        Account user=accountService.findByID(user_id);

            CoursePlan coursePlan=new CoursePlan();
            coursePlan.setParticipants(new ArrayList<>());
            coursePlan.setCreator(user);
            coursePlan.setCreationTime(LocalDateTime.now());
            coursePlan.setStartTime(LocalDateTime.now());
            coursePlan.setEndTime(LocalDateTime.now());
            coursePlan.setExams(new ArrayList<>());
            model.addAttribute("coursePlan",coursePlan);
            model.addAttribute("user",accountService.findByID(user_id));
            model.addAttribute("courses",courseRepository.findAll());
            return "addCoursePlan";

    }
    @PostMapping("/savecourseplan/{user_id}")
    public String saveCoursePlan(@ModelAttribute("course") CoursePlan coursePlan,@PathVariable("user_id") Long id) {
        System.out.println(coursePlan.getName());
        coursePlanRepository.save(coursePlan);
        return "redirect:/addcourseplan/" + id;
    }
}
