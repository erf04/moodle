package com.example.moodle.service.impl;

import com.example.moodle.model.Account;
import com.example.moodle.model.Course;
import com.example.moodle.model.CoursePlan;
import com.example.moodle.model.Teacher;
import com.example.moodle.repository.CoursePlanRepository;
import com.example.moodle.service.CoursePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursePlanServiceImpl implements CoursePlanService {

    @Autowired
    private CoursePlanRepository coursePlanRepository;

    @Override

    public List<CoursePlan> findByCourse(Course course) {
        return coursePlanRepository.findCoursePlansByCourse(course);
    }
    public List<CoursePlan> findCoursePlansByTeacher(Teacher teacher) {
        return coursePlanRepository.findCoursePlansByCreator(teacher);

    }
}
