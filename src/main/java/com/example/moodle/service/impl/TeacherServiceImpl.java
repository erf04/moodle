package com.example.moodle.service.impl;


import com.example.moodle.model.Teacher;
import com.example.moodle.repository.TeacherRepository;
import com.example.moodle.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public Teacher findTeacherById(Long id) {
        return teacherRepository.getReferenceById(id);
    }

    @Override
    public Teacher findTeacherByUsernameAndPassword(String username, String password) {
        return teacherRepository.findTeacherByUserNameAndPassword(username,password);
    }
}
