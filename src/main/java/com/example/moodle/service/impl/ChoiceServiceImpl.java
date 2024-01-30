package com.example.moodle.service.impl;

import com.example.moodle.model.Choice;
import com.example.moodle.repository.ChoiceRepository;
import com.example.moodle.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoiceServiceImpl implements ChoiceService {

    @Autowired
    private ChoiceRepository choiceRepository;
    @Override
    public Choice findById(Long id) {
        return choiceRepository.getReferenceById(id);
    }

    @Override
    public Choice save(Choice choice) {
        return choiceRepository.save(choice);
    }
}
