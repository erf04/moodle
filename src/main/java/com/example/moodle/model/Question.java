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
    @ManyToMany
    private List<Exam> exams;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "question")
    private List<Choice> choices;

    @CreationTimestamp
    private LocalDateTime creationTime;
}
