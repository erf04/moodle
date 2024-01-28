package com.example.moodle.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ExamPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int score;
    private LocalDateTime attendingDate;

    @ManyToOne
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
