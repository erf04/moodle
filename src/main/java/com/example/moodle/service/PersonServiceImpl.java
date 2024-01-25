package com.example.moodle.service;

import com.example.moodle.model.Person;
import com.example.moodle.repository.PersonRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        if (personRepository.findById(id).isPresent()){
            return personRepository.findById(id).get();
        }
        return null;

    }

    @Override
    public List<Person> findByFirstName(String firstname) {
        return personRepository.findPersonsByFirstName(firstname);
    }
}
