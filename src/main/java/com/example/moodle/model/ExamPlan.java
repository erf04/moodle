package com.example.moodle.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ExamPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int score;
    private LocalDateTime attendingDate;

    @ManyToMany
    @JoinTable(
            name = "examPlan_account",
            joinColumns = @JoinColumn(name = "examPlan_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> accounts;

    @ManyToOne
    private Exam exam;

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getAttendingDate() {
        return attendingDate;
    }

    public void setAttendingDate(LocalDateTime attendingDate) {
        this.attendingDate = attendingDate;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
