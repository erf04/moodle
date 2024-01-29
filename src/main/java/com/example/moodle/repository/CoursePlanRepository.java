package com.example.moodle.repository;

import com.example.moodle.model.Account;
import com.example.moodle.model.Course;
import com.example.moodle.model.CoursePlan;
import com.example.moodle.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePlanRepository extends JpaRepository<CoursePlan,Long> {
<<<<<<< HEAD
    List<CoursePlan> findCoursePlansByCourse(Course course);
=======
    List<CoursePlan> findCoursePlansByCreator(Teacher teacher);
>>>>>>> courseBranch
}
