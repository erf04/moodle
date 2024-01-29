package com.example.moodle.repository;

import com.example.moodle.model.Account;
import com.example.moodle.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
