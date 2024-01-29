package com.example.moodle.service;

import com.example.moodle.model.Exam;
import org.springframework.stereotype.Service;

@Service
public interface ExamService {
    public Exam findExamById(Long id);
    public Exam save(Exam exam);
}
