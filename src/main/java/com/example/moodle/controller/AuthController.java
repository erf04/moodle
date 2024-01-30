package com.example.moodle.controller;

import com.example.moodle.model.*;
import com.example.moodle.repository.CoursePlanRepository;
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

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        Account account =new Account();
        Person person=new Person();
        model.addAttribute("account",account);
        model.addAttribute("person",person);
        return "registerform";
    }

    @PostMapping("/register/save")
    public String registration(@Validated @ModelAttribute("user") Account account,
                               BindingResult result,
                               Model model){
        Account existingAccount= accountService.findByEmail(account.getEmail());
        if(existingAccount != null && existingAccount.getEmail() != null && !existingAccount.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }

        Account existingUsername=accountService.findByUsername(account.getUserName());
        if (existingUsername!=null && existingUsername.getUserName()!=null && !existingAccount.getUserName().isEmpty()){
            return "redirect:/register?fail";
        }
        if(result.hasErrors()){
            model.addAttribute("user", account);
            return "redirect:/register";
        }
        accountService.save(account);
        return "redirect:/home/"+account.getId()+"?register=success";
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
            return "adminLogin";
        }
        else {
            //System.out.println("REGULAR ACCOUNT");
            return "userhome";
        }
    }
}
