package com.example.moodle.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    @ManyToMany(mappedBy = "questions")
    private List<Exam> exams;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "question")
    private List<Choice> choices;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "question")
    private List<SubmittedAnswer> submittedAnswers;

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

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public List<SubmittedAnswer> getSubmittedAnswers() {
        return submittedAnswers;
    }

    public void setSubmittedAnswers(List<SubmittedAnswer> submittedAnswers) {
        this.submittedAnswers = submittedAnswers;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @CreationTimestamp
    private LocalDateTime creationTime;
}
