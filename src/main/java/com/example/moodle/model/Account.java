package com.example.moodle.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "accounts")
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
    private String userName;
    private String password;
    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;
    private String email;

    public List<CoursePlan> getAttendedCoursePlans() {
        return attendedCoursePlans;
    }

    public void setAttendedCoursePlans(List<CoursePlan> attendedCoursePlans) {
        this.attendedCoursePlans = attendedCoursePlans;
    }

    public List<Exam> getAttendedExams() {
        return attendedExams;
    }

    public void setAttendedExams(List<Exam> attendedExams) {
        this.attendedExams = attendedExams;
    }

    @ManyToMany(mappedBy = "participants")
    private List<CoursePlan> attendedCoursePlans;

    @ManyToMany (mappedBy = "participants")
    private List<Exam> attendedExams;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
