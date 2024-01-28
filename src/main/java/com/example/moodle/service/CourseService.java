package com.example.moodle.service;

import com.example.moodle.model.Course;
import org.springframework.stereotype.Service;


@Service
public interface CourseService {
    public Course save(Course course);
    public Course getCourseById(Long id);
}
