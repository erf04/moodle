package com.example.moodle.service;

import com.example.moodle.model.SubmittedAnswer;

public interface SubmittedAnswerService {
    SubmittedAnswer findById(Long id);
    SubmittedAnswer save(SubmittedAnswer submittedAnswer);
}
