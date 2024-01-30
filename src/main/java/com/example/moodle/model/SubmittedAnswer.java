package com.example.moodle.model;

import jakarta.persistence.*;

@Entity
public class SubmittedAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    Account submitter;

    @ManyToOne
    private Choice userChoice;

    @ManyToOne
    private Question question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Account submitter) {
        this.submitter = submitter;
    }

    public Choice getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(Choice userChoice) {
        this.userChoice = userChoice;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
