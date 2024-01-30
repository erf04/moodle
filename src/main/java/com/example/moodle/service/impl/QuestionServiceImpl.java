package com.example.moodle.service.impl;

import com.example.moodle.model.Choice;
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

    @Override
    public Long findCorrectChoiceIdByQuestionID(Long id) {
        Question question=this.findById(id);
        for (Choice choice: question.getChoices()){
            if (choice.isCorrect()){
                return choice.getId();
            }
        }
        return 0L;
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }
}
