package com.example.moodle.repository;

import com.example.moodle.model.Account;
import com.example.moodle.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAllByPerson(Person person);
    Account findByUserNameAndPassword(String username,String password);
    Account findByEmail(String email);
    Account findByUserName(String username);
    Account findAccountById(Long id);
}
