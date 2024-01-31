package com.example.moodle.service.impl;

import com.example.moodle.model.Account;
import com.example.moodle.model.CoursePlan;
import com.example.moodle.repository.AccountRepository;
import com.example.moodle.repository.CoursePlanRepository;
import com.example.moodle.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CoursePlanRepository coursePlanRepository;

    public void addCoursePlanToAccount(Long accountId, Long coursePlanId) {
        // Retrieve the Account and CoursePlan entities
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        Optional<CoursePlan> optionalCoursePlan = coursePlanRepository.findById(coursePlanId);

        if (optionalAccount.isPresent() && optionalCoursePlan.isPresent()) {
            // Add CoursePlan to Account and vice versa
            Account account = optionalAccount.get();
            CoursePlan coursePlan = optionalCoursePlan.get();

            account.getAttendedCoursePlans().add(coursePlan);
            coursePlan.getParticipants().add(account);

            // Save the changes
            accountRepository.save(account);
            coursePlanRepository.save(coursePlan);
        } else {
            // Handle the case when either Account or CoursePlan is not found
            // (throw an exception, log a message, etc.)
        }
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

    @Override
    public List<CoursePlan> findCoursePlansByAccountId(Long id) {
        return accountRepository.findCoursePlansByAccountId(id);
    }

}
