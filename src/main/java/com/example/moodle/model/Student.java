package com.example.moodle.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends Account{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
