package com.example.moodle.model;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;

@Entity
@Table(name = "moodleUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
