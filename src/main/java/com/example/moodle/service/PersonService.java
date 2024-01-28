package com.example.moodle.service;

import com.example.moodle.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    public List<Person> findAllPersons();
    public Person findById(Long id);
    public List<Person> findByFirstName(String firstname);
    public Person save(Person person);

}
