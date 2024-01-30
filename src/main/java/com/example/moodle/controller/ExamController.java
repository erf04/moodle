package com.example.moodle.controller;

import com.example.moodle.model.*;
import com.example.moodle.repository.ChoiceRepository;
import com.example.moodle.repository.CoursePlanRepository;
import com.example.moodle.repository.ExamPlanRepository;
import com.example.moodle.repository.PersonRepository;
import com.example.moodle.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class ExamController {


    @Autowired
    private AccountService accountService;

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private  ChoiceService choiceService;

    @Autowired
    private SubmittedAnswerService submittedAnswerService;

    @Autowired
    private ExamPlanRepository examPlanRepository;


    @GetMapping("/show-exam/{user_id}/{exam_id}/")
    public String showExam(@PathVariable("user_id") Long user_id, @PathVariable("exam_id")Long exam_id,
                            Model model){

        Account account=accountService.findByID(user_id);
        List<Question> questions=questionService.findByExamID(exam_id);
        Exam exam=examService.findExamById(exam_id);
//        model.addAttribute("questions",questions);
        model.addAttribute("user",account);
        model.addAttribute("exam",exam);
        return "exam";

    }

    @PostMapping("/submit-exam/{user_id}/{exam_id}")
    public String submitExam(@PathVariable("user_id") Long user_id,
                                 @PathVariable("exam_id") Long exam_id,
                                 @ModelAttribute Account account,
                                 Model model){

        Account user=accountService.findByID(user_id);
        Exam exam= examService.findExamById(exam_id);
        for (Map.Entry<Long,Long> map:account.getSubmittedAnswers().entrySet()){
            SubmittedAnswer submittedAnswer=new SubmittedAnswer();
            submittedAnswer.setUserChoice(choiceService.findById(map.getValue()));
            submittedAnswer.setQuestion(questionService.findById(map.getKey()));
            submittedAnswer.setSubmitter(user);
            submittedAnswerService.save(submittedAnswer);
        }
        ExamPlan examPlan=new ExamPlan();
        examPlan.setExam(exam);
        examPlan.setAccount(user);
        int score=examService.calculateScore(account.getSubmittedAnswers());
        examPlan.setScore(score);
        examPlan.setAttendingDate(LocalDateTime.now());
        examPlanRepository.save(examPlan);
        model.addAttribute("score",score);
        model.addAttribute("user",accountService.findByID(user_id));
        return "ExamResult";



    }


}
