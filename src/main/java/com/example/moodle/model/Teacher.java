package com.example.moodle.model;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "teachers")
public class Teacher extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "creator")
    private List<CoursePlan> coursePlans;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
