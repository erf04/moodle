package com.example.moodle.controller;

import com.example.moodle.model.*;
import com.example.moodle.repository.CoursePlanRepository;
import com.example.moodle.repository.CourseRepository;
import com.example.moodle.repository.ExamPlanRepository;
import com.example.moodle.service.AccountService;
import com.example.moodle.service.CoursePlanService;

import com.example.moodle.service.ExamService;
import com.example.moodle.service.TeacherService;
import com.example.moodle.service.impl.ExamServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CourseController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CoursePlanRepository coursePlanRepository;
    @Autowired
    private CoursePlanService coursePlanService;
    @Autowired
    private ExamService examService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ExamPlanRepository examPlanRepository;

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
            return "redirect:/home/"+id;
        }
        else{
            return "error500";
        }
    }

    @GetMapping("/addcourseplan/{user_id}")
    public String showAddCoursePlan(@PathVariable Long user_id, Model model) {
        Account user=accountService.findByID(user_id);
        model.addAttribute("user",user);
        if(user instanceof Teacher){
            CoursePlan coursePlan=new CoursePlan();
            model.addAttribute("coursePlan",coursePlan);
            model.addAttribute("courses",courseRepository.findAll());
            return "addCoursePlan";
        }
        else{
            return "error500";
        }
    }
    @PostMapping("/savecourseplan/{user_id}")
    public  String courseplanSave(@ModelAttribute("courseplan") CoursePlan coursePlan,@PathVariable("user_id") Long id){
        System.out.println(coursePlan.getName());
        Account teacher= teacherService.findTeacherById(id);
        if (teacher!=null){
            coursePlan.setCreator(teacher);
            coursePlanRepository.save(coursePlan);
            return "redirect:/addcourseplan/"+id;
        }
        else{
            return "error500";
        }
    }

    @PostMapping("/searchCourse/{user_id}")
    public  String courseSearch(Model model,@RequestParam("searchedContent") String partialCourseName,@PathVariable("user_id") Long id){
        Account account=accountService.findByID(id);
        List<CoursePlan> coursePlans=accountService.findCoursePlansByAccountId(id);
        model.addAttribute("user",account);
        model.addAttribute("courseplans",coursePlans);
        List<Course> courses=courseRepository.findAllByNameContaining(partialCourseName);
        List<CoursePlan> coursePlansNew=new ArrayList<>();
        if (courses.size()>0) {
            for (Course course : courses) {
                coursePlansNew.addAll(coursePlanService.findByCourse(course));
            }
        }
        model.addAttribute("searchedCoursePlans",coursePlansNew);
        return "searched";
    }
    @GetMapping("/seeCoursePlan/{user_id}/{course_id}")
    public String seeCoursePlan(@PathVariable("course_id") long course_id,@PathVariable("user_id") long user_id,Model model){
        Account account=accountService.findByID(user_id);
        CoursePlan coursePlan=coursePlanRepository.getReferenceById(course_id);
        if (account instanceof Teacher){
        List<CoursePlan> coursePlans=coursePlanRepository.findCoursePlansByCreator((Teacher) account);
        if(coursePlans.contains(coursePlan)) {
            return "teacherCourseForm";
        }
        }

        model.addAttribute("user",account);
        model.addAttribute("courseplan",coursePlan);
        if (coursePlan.getParticipants().contains(account)) {
            model.addAttribute("booleanVar",true);
            return "courseform";
        }
        else{model.addAttribute("booleanVar",false);}


            List<CoursePlan> coursePlans=account.getAttendedCoursePlans();
            List<Integer> examPlan = new ArrayList<>();
            for (int i = 0; i < coursePlan.getExams().size(); i++) {
                if (examPlanRepository.findExamPlanByExamAndAccount(coursePlan.getExams().get(i), account) == null) {
                    examPlan.add(-1);
                    continue;
                }
                examPlan.add((examPlanRepository.findExamPlanByExamAndAccount(coursePlan.getExams().get(i), account)).getScore());
            }



            model.addAttribute("examPlans", examPlan);
            return "courseform";


    }


    @PostMapping("/courseplan/{coursePlanId}/{user_id}")
    public String joinCoursePlan(@PathVariable("coursePlanId") Long coursePlanId, @PathVariable("user_id") long user_id) {
        CoursePlan coursePlan = coursePlanRepository.getReferenceById(coursePlanId);
        Account user= accountService.findByID(user_id);
        coursePlan.getParticipants().add(user);
        coursePlanRepository.save(coursePlan);
        return "redirect:/home/"+user_id;
    }

}
