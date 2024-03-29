package com.example.moodle.model;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "teachers")

public class Teacher extends Account{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "creator")
    private List<CoursePlan> coursePlans;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "creator")
    private List<Exam> exams;

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<CoursePlan> getCoursePlans() {
        return coursePlans;
    }

    public void setCoursePlans(List<CoursePlan> coursePlans) {
        this.coursePlans = coursePlans;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
