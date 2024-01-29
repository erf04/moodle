package com.example.moodle.controller;

import com.example.moodle.model.*;
import com.example.moodle.repository.CoursePlanRepository;
import com.example.moodle.repository.PersonRepository;
import com.example.moodle.service.AccountService;
import com.example.moodle.service.CourseService;
import com.example.moodle.service.ExamService;
import com.example.moodle.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ExamController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ExamService examService;

    @Autowired
    private PersonRepository personService;
    @GetMapping("/add")
    public String addCourse(){
        Teacher teacher=new Teacher();
        teacher.setUserName("teacher");
        teacher.setPassword("0000");
        teacher.setPerson(personService.getReferenceById(1L));
        accountService.save(teacher);
        Exam exam=new Exam();
        Question question=new Question();
        question.setText("this is question 1");
        Choice choice1=new Choice();
        choice1.setText("choice1");
        choice1.setCorrect(false);
        Choice choice2=new Choice();
        choice2.setText("choice2");
        choice2.setCorrect(true);
        question.setChoices(List.of(choice1,choice2));
        question.setExams(List.of(exam));
        exam.setCreator(teacher);
        ExamPlan examPlan=new ExamPlan();
        examPlan.setExam(exam);
//        exam.setCoursePlan(coursePlanRepository.getReferenceById(1L));
        examPlan.setAccounts(List.of(accountService.findByID(1L),accountService.findByID(2L)));
        exam.setQuestions(List.of(question));
        exam.setExamPlans(List.of(examPlan));
        examService.save(exam);
        return "test";
    }

    @GetMapping("/show-exam/{user_id}/{exam_id}/")
    public String showExam(@PathVariable("user_id") Long user_id, @PathVariable("exam_id")Long exam_id,
                            Model model){

        Exam exam=examService.findExamById(exam_id);
        Account account=accountService.findByID(user_id);
        List<Question> questions=exam.getQuestions();
        model.addAttribute("questions",questions);
        model.addAttribute("user",account);
        return "exam";

    }

//    @PostMapping("/submit-question/{user_id}/{question_id}")
//    public String submitQuestion(@PathVariable("user_id") Long user_id,
//                                 @PathVariable("question_id") Long question_id,
//                                 @RequestParam("choice_id") Long choice_id,
//                                 @RequestParam("index") Integer index,
//                                 Model model){
//
//        SubmittedAnswer submittedAnswer=new SubmittedAnswer();
//        submittedAnswer.setQuestion();
//        submittedAnswer.setSubmitter(accountService.findByID(user_id));
//        submittedAnswer.setUserChoice();
//
//    }


}
