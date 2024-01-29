package com.example.moodle.service.impl;

import com.example.moodle.model.Question;
import com.example.moodle.repository.QuestionRepository;
import com.example.moodle.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public List<Question> findByExamID(Long id) {
        return questionRepository.findQuestionByExamID(id);
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.getReferenceById(id);
    }
}
