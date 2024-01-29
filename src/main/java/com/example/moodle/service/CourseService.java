package com.example.moodle.service;

import com.example.moodle.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CourseService {
    public Course save(Course course);
    public Course getCourseById(Long id);
    List<Course> findAllByNameContaining(String name);
}
