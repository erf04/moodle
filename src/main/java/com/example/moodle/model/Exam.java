package com.example.moodle.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    private CoursePlan coursePlan;

    @ManyToMany
    @JoinTable(
        name = "exam_questions",
        joinColumns = @JoinColumn(name = "exam_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;

    @ManyToOne
    private Teacher creator;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "exam")
    private List<ExamPlan> examPlans;

    private LocalDateTime examTime;

    public Map<Long, Choice> getSelectedChoiceIds() {
        return selectedChoiceIds;
    }

    public void setSelectedChoiceIds(Map<Long, Choice> selectedChoiceIds) {
        this.selectedChoiceIds = selectedChoiceIds;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private Map<Long, Choice> selectedChoiceIds;
    public LocalDateTime getExamTime() {
        return examTime;
    }

    public void setExamTime(LocalDateTime examTime) {
        this.examTime = examTime;
    }

    public List<ExamPlan> getExamPlans() {
        return examPlans;
    }

    public void setExamPlans(List<ExamPlan> examPlans) {
        this.examPlans = examPlans;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CoursePlan getCoursePlan() {
        return coursePlan;
    }

    public void setCoursePlan(CoursePlan coursePlan) {
        this.coursePlan = coursePlan;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Account getCreator() {
        return this.getCoursePlan().getCreator();
    }

    public void setCreator(Teacher creator) {
        this.creator = creator;
    }
}
