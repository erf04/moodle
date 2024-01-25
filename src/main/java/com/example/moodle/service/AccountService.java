package com.example.moodle.service;

import com.example.moodle.model.Account;
import com.example.moodle.model.Person;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService{
    List<Account> findAllByPerson(Person person);
    Account findByUserNameAndPassword(String username,String password);
    Account findByEmail(String email);
    Account save(Account account);
    Account findByUsername(String username);
    Account findByID(Long id);
}
