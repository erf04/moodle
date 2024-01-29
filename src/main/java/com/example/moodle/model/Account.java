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

    @ManyToMany(mappedBy = "participants")
    private List<CoursePlan> attendedCoursePlans;

    @ManyToMany(mappedBy = "accounts")
    private List<ExamPlan> examPlans;

    public List<ExamPlan> getExamPlans() {
        return examPlans;
    }

    public void setExamPlans(List<ExamPlan> examPlans) {
        this.examPlans = examPlans;
    }

    public List<SubmittedAnswer> getSubmittedAnswers() {
        return submittedAnswers;
    }

    public void setSubmittedAnswers(List<SubmittedAnswer> submittedAnswers) {
        this.submittedAnswers = submittedAnswers;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "submitter")
    private List<SubmittedAnswer> submittedAnswers;


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
