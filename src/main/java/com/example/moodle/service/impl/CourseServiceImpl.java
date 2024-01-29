package com.example.moodle.service.impl;

import com.example.moodle.model.Course;
import com.example.moodle.repository.CourseRepository;
import com.example.moodle.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return null;
    }

    @Override
    public List<Course> findAllByNameContaining(String name) {
        return courseRepository.findAllByNameContaining(name);
    }
}
