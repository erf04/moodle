package com.example.moodle.controller;

import com.example.moodle.model.Account;
import com.example.moodle.model.Person;
import com.example.moodle.service.AccountService;
import com.example.moodle.service.CoursePlanService;
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
    private CoursePlanService coursePlanService;
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
        return "redirect:/home/"+findingAccount.getId();
    }

    @GetMapping("/home/{user_id}")
    public String home(@PathVariable Long user_id,Model model){
        Account account=accountService.findByID(user_id);
        System.out.println(coursePlanService.findCoursePlanByParticipants(List.of(account)).isEmpty());
        model.addAttribute("user",accountService.findByID(user_id));
        return "userhome";
    }
}
