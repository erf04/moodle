package com.example.moodle.service;

import com.example.moodle.model.Choice;
import com.example.moodle.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    public List<Question> findByExamID(Long id);
    Question findById(Long id);

    Long findCorrectChoiceIdByQuestionID(Long id);

    Question save(Question question);
}
