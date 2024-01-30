package com.example.moodle.repository;

import com.example.moodle.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Teacher findTeacherByUserNameAndPassword(String username,String password);
}
