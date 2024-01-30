package com.example.moodle.repository;

import com.example.moodle.model.SubmittedAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmittedAnswerRepository extends JpaRepository<SubmittedAnswer,Long> {
}
