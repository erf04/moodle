package com.example.moodle.service;

import com.example.moodle.model.Choice;
import org.springframework.stereotype.Service;


@Service
public interface ChoiceService {
    Choice findById(Long id);
    Choice save(Choice choice);
}
