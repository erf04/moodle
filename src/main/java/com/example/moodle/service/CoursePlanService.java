package com.example.moodle.service;

import com.example.moodle.model.Account;
import com.example.moodle.model.CoursePlan;
import com.example.moodle.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoursePlanService {
    List<CoursePlan> findCoursePlansByTeacher(Teacher teacher);
}
