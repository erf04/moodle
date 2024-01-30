package com.example.moodle.service.impl;

import com.example.moodle.model.Choice;
import com.example.moodle.model.Exam;
import com.example.moodle.model.Question;
import com.example.moodle.repository.ExamRepository;
import com.example.moodle.service.ExamService;
import com.example.moodle.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service

public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionService questionService;
    @Override
    public Exam findExamById(Long id) {
        return examRepository.getReferenceById(id);
    }

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public int calculateScore(Map<Long,Long> questionChoiceMap) {
        int result=0;
        for (Map.Entry<Long,Long> map:questionChoiceMap.entrySet()){
            Question question=questionService.findById(map.getKey());
            Long CorrectId=questionService.findCorrectChoiceIdByQuestionID(map.getKey());
            if (Objects.equals(map.getValue(), CorrectId)){
                result++;
            }
        }
        return result;

    }
}
