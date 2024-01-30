package com.example.moodle.service;

import com.example.moodle.model.Exam;
import com.example.moodle.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface
ExamService {
    public Exam findExamById(Long id);
    public Exam save(Exam exam);

    int calculateScore(Map<Long,Long> questionChoiceMap);
    List<Exam> findExamsByCreator(Teacher teacher);
}
