package com.example.moodle.repository;

import com.example.moodle.model.CoursePlan;
import com.example.moodle.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("SELECT a.questions FROM Exam a WHERE a.id = :examId")
    List<Question> findQuestionByExamID(@Param("examId") Long examId);
}
