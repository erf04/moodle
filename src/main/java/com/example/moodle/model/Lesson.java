package com.example.moodle.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "lesson")
    private List<Exam> examList=new ArrayList<>();
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
