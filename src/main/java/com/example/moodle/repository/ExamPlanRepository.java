package com.example.moodle.repository;

import com.example.moodle.model.Account;
import com.example.moodle.model.Exam;
import com.example.moodle.model.ExamPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExamPlanRepository extends JpaRepository<ExamPlan,Long> {
    ExamPlan findExamPlanByExamAndAccount(Exam exam, Account account);

    List<ExamPlan> findAllByExam(Exam exam);
}
