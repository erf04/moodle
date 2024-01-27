package com.example.moodle.service.impl;

import com.example.moodle.model.Account;
import com.example.moodle.model.Person;
import com.example.moodle.repository.AccountRepository;
import com.example.moodle.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Account> findAllByPerson(Person person) {
        return accountRepository.findAllByPerson(person);
    }

    @Override
    public Account findByUserNameAndPassword(String username, String password) {
        return accountRepository.findByUserNameAndPassword(username,password);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUserName(username);
    }

    @Override
    public Account findByID(Long id) {
        return accountRepository.findAccountById(id);
    }

}
