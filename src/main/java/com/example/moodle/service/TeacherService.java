package com.example.moodle.service;

import com.example.moodle.model.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
     Teacher findTeacherById(Long id);

     Teacher findTeacherByUsernameAndPassword(String username,String password);

}
