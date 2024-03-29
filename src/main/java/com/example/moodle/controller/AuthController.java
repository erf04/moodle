package com.example.moodle.controller;


import com.example.moodle.model.*;
import com.example.moodle.model.Account;
import com.example.moodle.model.CoursePlan;
import com.example.moodle.model.Teacher;
import com.example.moodle.repository.CoursePlanRepository;
import com.example.moodle.repository.CourseRepository;
import com.example.moodle.service.AccountService;
import com.example.moodle.service.CoursePlanService;
import com.example.moodle.service.TeacherService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CoursePlanRepository coursePlanRepository;

    @Autowired
    private CourseRepository courseRepository;



    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        Account account =new Account();
        model.addAttribute("account",account);
        return "registerform";
    }

    @PostMapping("/register/save")
    public String registration(@Validated @ModelAttribute("user") Account account,
                               @RequestParam("role") String role,
                               BindingResult result,
                               Model model){
        Account existingAccount= accountService.findByEmail(account.getEmail());
        if(existingAccount != null && existingAccount.getEmail() != null && !existingAccount.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }

        Account existingUsername=accountService.findByUsername(account.getUserName());
        if (existingUsername!=null && existingUsername.getUserName()!=null && !existingUsername.getUserName().isEmpty()){
            return "redirect:/register?fail";
        }
        if(result.hasErrors()){
            model.addAttribute("user", account);
            return "redirect:/register";
        }
//        account.getPerson().setId(account.getId());
//        account.setId(account.getPerson().getId());
//        accountService.save(account);
        System.out.println(role);
        if (role == null) {
            model.addAttribute("roles", new String[] {"teacher", "student"});
            return "registerform";
        }

        if (role.equals("teacher")) {
            Teacher teacher = new Teacher();
            teacher.setUserName(account.getUserName());
            teacher.setEmail(account.getEmail());
            teacher.setPassword(account.getPassword());
            accountService.save(teacher);
            return "redirect:/home/"+teacher.getId()+"?register=success";
        } else {
            Account student = new Account();
            student.setUserName(account.getUserName());
            student.setEmail(account.getEmail());
            student.setPassword(account.getPassword());
            accountService.save(student);
            return "redirect:/home/"+student.getId()+"?register=success";
        }

    }

    @GetMapping("/")
    public String showLoginForm(Model model){
        return "loginform";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,@RequestParam String password){
        Account findingAccount=accountService.findByUserNameAndPassword(username,password);
        if (findingAccount==null){
            return "redirect:/login?fail";
        }
        if (findingAccount.getId()!=null){
            return "redirect:/home/"+findingAccount.getId();
        }
//        Teacher findingTeacher=teacherService.findTeacherByUsernameAndPassword(username,password);
        return "redirect:/home/"+findingAccount.getId();


    }

    @GetMapping("/home/{user_id}")
    public String home(@PathVariable Long user_id,Model model) {
        Account account = accountService.findByID(user_id);
        List<CoursePlan> coursePlans = accountService.findCoursePlansByAccountId(user_id);
        model.addAttribute("user", account);
        model.addAttribute("courseplans", coursePlans);

//        for (CoursePlan coursePlan : coursePlans)
//            System.out.println(coursePlan.getCourse().getName() + "    " + coursePlan.getCourse().getId());
        if (account instanceof Teacher){
            //System.out.println("TEACHER ACCOUNT");
            List<CoursePlan> myCoursePlans = coursePlanRepository.findCoursePlansByCreator((Teacher) account);
            model.addAttribute("myCourses", myCoursePlans);
            return "teacherLogin";
    }
        else if (account instanceof Admin) {
            //System.out.println("ADMIN ACCOUNT");
            List<Course> courses=courseRepository.findAll();
            System.out.println(courses.size());
            model.addAttribute("courses",courses);
            return "adminLogin";
        }
        else {
            //System.out.println("REGULAR ACCOUNT");
            return "userhome";
        }
    }

    @GetMapping("/profile/{user_id}")
    public String profile(@PathVariable long user_id,Model model){
        Account account=accountService.findByID(user_id);
        List<CoursePlan> coursePlans=accountService.findCoursePlansByAccountId(user_id);
        if(account instanceof Teacher){
            model.addAttribute("created",coursePlanRepository.findCoursePlansByCreator((Teacher) account));
        }
        System.out.println(coursePlans.isEmpty());
        model.addAttribute("user",account);
        model.addAttribute("courseplans",coursePlans);
        return "profile";
    }

    @GetMapping("/editprofile/{user_id}")
    public String editprof(@PathVariable long user_id,Model model){
        Account account=accountService.findByID(user_id);
        List<CoursePlan> coursePlans=accountService.findCoursePlansByAccountId(user_id);
        model.addAttribute("user",account);
        model.addAttribute("courseplans",coursePlans);
        return "editprofile";
    }
    @PostMapping("/saveprofile/{user_id}")
    public String saveprof(@PathVariable long user_id,@ModelAttribute Account user){
        Account user1 =accountService.findByID(user_id);
        user1.setEmail(user.getEmail());
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        accountService.save(user1);
        return "redirect:/editprofile/"+user_id;
    }

}
