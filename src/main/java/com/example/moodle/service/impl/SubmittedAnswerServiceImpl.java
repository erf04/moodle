package com.example.moodle.service.impl;

import com.example.moodle.model.SubmittedAnswer;
import com.example.moodle.repository.SubmittedAnswerRepository;
import com.example.moodle.service.SubmittedAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmittedAnswerServiceImpl implements SubmittedAnswerService {

    @Autowired
    private SubmittedAnswerRepository submittedAnswerRepository;
    @Override
    public SubmittedAnswer findById(Long id) {
        return submittedAnswerRepository.getReferenceById(id);
    }

    @Override
    public SubmittedAnswer save(SubmittedAnswer submittedAnswer) {
        return submittedAnswerRepository.save(submittedAnswer);
    }
}
