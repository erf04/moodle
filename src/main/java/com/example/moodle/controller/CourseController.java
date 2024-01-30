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
            return "redirect:/addcourse/"+id;
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
        System.out.println(partialCourseName);
        List<Course> courses=courseRepository.findAllByNameContaining(partialCourseName);
        List<CoursePlan> coursePlansNew=new ArrayList<>();
        for (Course course:courses){
            coursePlansNew.addAll(coursePlanService.findByCourse(course));
        }
        model.addAttribute("searchedCoursePlans",coursePlansNew);
        System.out.println(coursePlans.get(0));
        return "searched";
    }
    @GetMapping("/seeCoursePlan/{user_id}/{course_id}")
    public String seeCoursePlan(@PathVariable("course_id") long course_id,@PathVariable("user_id") long user_id,Model model){
        List<CoursePlan> coursePlans=accountService.findCoursePlansByAccountId(user_id);
        model.addAttribute("courseplans",coursePlans);
        Account account=accountService.findByID(user_id);
        model.addAttribute("user",account);
        CoursePlan coursePlan=coursePlanRepository.getReferenceById(course_id);
        model.addAttribute("courseplan",coursePlan);
        if (account instanceof Teacher) {
            model.addAttribute("booleanVar",false);
            return "teacherCourseForm";
        }
        else
        {
            boolean booleanVar=coursePlans.contains(coursePlan);
            List<Integer> examPlan = new ArrayList<>();
            for (int i = 0; i < coursePlan.getExams().size(); i++) {
                if (examPlanRepository.findExamPlanByExamAndAccount(coursePlan.getExams().get(i), account) == null) {
                    examPlan.add(-1);
                    continue;
                }
                examPlan.add((examPlanRepository.findExamPlanByExamAndAccount(coursePlan.getExams().get(i), account)).getScore());
            }
<<<<<<< HEAD
            boolean flag = false;
            for (CoursePlan plan : coursePlans) {
                if (plan == coursePlan) {
                    model.addAttribute("booleanVar", true);
                    flag = true;
                }
            }
            if (!flag) model.addAttribute("booleanVar",false);
=======
//            boolean flag = false;
//            for (int j=0; j<coursePlans.size(); j++) {
//                if (coursePlans.get(j)==coursePlan) {
//                    model.addAttribute("booleanVar", booleanVar);
//                    flag =true;
//                }
//            }
//            if (!flag) model.addAttribute("booleanVar",false);
            model.addAttribute("booleanVar",booleanVar);
>>>>>>> cd012c9805607a1cd914969b3bdb3fd033048b71
            model.addAttribute("examPlans", examPlan);
            return "courseform";
        }

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
