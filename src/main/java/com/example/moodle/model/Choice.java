package com.example.moodle.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @ManyToOne
    private Question question;
    private boolean isCorrect;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "userChoice")
    private List<SubmittedAnswer> submittedAnswer;

    public List<SubmittedAnswer> getSubmittedAnswer() {
        return submittedAnswer;
    }

    public void setSubmittedAnswer(List<SubmittedAnswer> submittedAnswer) {
        this.submittedAnswer = submittedAnswer;
    }
}
