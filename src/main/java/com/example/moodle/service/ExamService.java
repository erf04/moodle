package com.example.moodle.service;

import com.example.moodle.model.Exam;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface
ExamService {
    public Exam findExamById(Long id);
    public Exam save(Exam exam);

    int calculateScore(Map<Long,Long> questionChoiceMap);
}
