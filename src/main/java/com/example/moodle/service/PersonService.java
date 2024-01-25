package com.example.moodle.service;

import com.example.moodle.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    List<Person> findAllPersons();
    Person findById(Long id);
    List<Person> findByFirstName(String firstname);

}
