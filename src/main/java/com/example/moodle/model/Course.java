package com.example.moodle.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<CoursePlan> coursePlans;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CoursePlan> getCoursePlans() {
        return coursePlans;
    }

    public void setCoursePlans(List<CoursePlan> coursePlans) {
        this.coursePlans = coursePlans;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
