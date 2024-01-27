package com.example.moodle.model;

import jakarta.persistence.*;

@Entity
@Table(name = "choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @ManyToOne
    private Question question;
    private boolean isCorrect;

}
