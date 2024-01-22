package com.example.moodle.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher extends Person{
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
