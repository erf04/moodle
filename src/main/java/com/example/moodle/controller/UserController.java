package com.example.moodle.controller;


import com.example.moodle.model.User;
import com.example.moodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String helloWorld(){
        return "hello";
    }

    @GetMapping("/list")
    public String getUsers(Model model){
        List<User> users=userService.getAllUsers();
        model.addAttribute("users",users);
        return "users";
    }

//    @PostMapping ("/add")
//    public User addUser(@ModelAttribute User user){
//
//    }

}
