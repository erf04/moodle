package com.example.moodle.repository;

import com.example.moodle.model.Account;
import com.example.moodle.model.CoursePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByUserNameAndPassword(String username,String password);
    Account findByEmail(String email);
    Account findByUserName(String username);
    Account findAccountById(Long id);

    @Query("SELECT a.attendedCoursePlans FROM Account a WHERE a.id = :accountId")
    List<CoursePlan> findCoursePlansByAccountId(@Param("accountId") Long accountId);

}
