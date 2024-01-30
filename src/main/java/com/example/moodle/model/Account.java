package com.example.moodle.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
    private String userName;
    private String password;
    private String email;

    private String firstName;
    private String lastName;

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

    public List<CoursePlan> getAttendedCoursePlans() {
        return attendedCoursePlans;
    }

    public void setAttendedCoursePlans(List<CoursePlan> attendedCoursePlans) {
        this.attendedCoursePlans = attendedCoursePlans;
    }

    @ManyToMany(mappedBy = "participants")
    private List<CoursePlan> attendedCoursePlans;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "account")
    private List<ExamPlan> examPlans;

    public List<ExamPlan> getExamPlans() {
        return examPlans;
    }

    public void setExamPlans(List<ExamPlan> examPlans) {
        this.examPlans = examPlans;
    }


//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "submitter")
    @ElementCollection
    private Map<Long,Long> submittedAnswers;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "submitter")
    private List<SubmittedAnswer> userSubmittedAnswers;

    public List<SubmittedAnswer> getUserSubmittedAnswers() {
        return userSubmittedAnswers;
    }

    public void setUserSubmittedAnswers(List<SubmittedAnswer> userSubmittedAnswers) {
        this.userSubmittedAnswers = userSubmittedAnswers;
    }

    public Map<Long, Long> getSubmittedAnswers() {
        return submittedAnswers;
    }

    public void setSubmittedAnswers(Map<Long, Long> submittedAnswers) {
        this.submittedAnswers = submittedAnswers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
