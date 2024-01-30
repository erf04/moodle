package com.example.moodle.repository;

import com.example.moodle.model.Exam;
import com.example.moodle.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
    List<Exam> findAllByCreator(Teacher teacher);
}
