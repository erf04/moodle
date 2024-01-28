package com.example.moodle.controller;

import com.example.moodle.model.Account;
import com.example.moodle.model.Course;
import com.example.moodle.model.CoursePlan;
import com.example.moodle.repository.CoursePlanRepository;
import com.example.moodle.service.AccountService;
import com.example.moodle.service.CourseService;
import com.example.moodle.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ExamController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CoursePlanRepository coursePlanRepository;
    @Autowired
    private AccountService accountService;
    @GetMapping("/add")
    public void addCourse(){
        Course course=new Course();
        course.setName("java");
        courseService.save(course);
        Course course1=new Course();
        course.setName("c");
        courseService.save(course1);
        CoursePlan coursePlan=new CoursePlan();
        coursePlan.setCourse(course);
        coursePlan.setStartTime(LocalDateTime.now());
        coursePlan.setEndTime(LocalDateTime.now().plusHours(2));
        Account account=accountService.findByID(1L);
        List<Account> accountList=List.of(account);
        coursePlan.setParticipants(accountList);
        coursePlanRepository.save(coursePlan);
    }

}
