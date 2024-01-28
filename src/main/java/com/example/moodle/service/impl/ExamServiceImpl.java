package com.example.moodle.service.impl;

import com.example.moodle.model.Exam;
import com.example.moodle.repository.ExamRepository;
import com.example.moodle.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;
    @Override
    public Exam findExamById(Long id) {
        return examRepository.getReferenceById(id);
    }

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }
}
