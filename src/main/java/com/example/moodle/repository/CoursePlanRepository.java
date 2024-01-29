package com.example.moodle.repository;

import com.example.moodle.model.Account;
import com.example.moodle.model.CoursePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePlanRepository extends JpaRepository<CoursePlan,Long> {
}
