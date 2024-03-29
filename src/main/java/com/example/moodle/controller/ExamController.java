package com.example.moodle.controller;

import com.example.moodle.model.*;
import com.example.moodle.repository.ExamPlanRepository;
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

    @Autowired
    private CoursePlanService coursePlanService;

    @Autowired
    private TeacherService teacherService;



    @GetMapping("/show-exam/{user_id}/{exam_id}")
    public String showExam(@PathVariable("user_id") Long user_id, @PathVariable("exam_id")Long exam_id,
                            Model model){

        Account account=accountService.findByID(user_id);
        List<Question> questions=questionService.findByExamID(exam_id);
        Exam exam=examService.findExamById(exam_id);
//        model.addAttribute("questions",questions);
        List<CoursePlan> coursePlans=accountService.findCoursePlansByAccountId(user_id);
        model.addAttribute("user",account);
        model.addAttribute("courseplans",coursePlans);
        model.addAttribute("exam",exam);
        return "exam";

    }

    @PostMapping("/submit-exam/{user_id}/{exam_id}")
    public String submitExam(@PathVariable("user_id") Long user_id,
                                 @PathVariable("exam_id") Long exam_id,
                                 @ModelAttribute Account account,
                                 Model model){
    try{
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
        List<CoursePlan> coursePlans=accountService.findCoursePlansByAccountId(user_id);
        model.addAttribute("user",account);
        model.addAttribute("courseplans",coursePlans);
        model.addAttribute("courseplan",exam.getCoursePlan());
        return "userexamresult";
    }catch (Exception e){
        return "twoAttend";
    }


    }



    @PostMapping("/save-exam/{user_id}/{course_id}")
    public String saveExam(@PathVariable("user_id")Long userId,@PathVariable("course_id") Long course_id,
                           @ModelAttribute Exam exam,
                           Model model){
        CoursePlan coursePlan=coursePlanService.findCoursePlanByID(course_id);
        exam.setCoursePlan(coursePlan);
        exam.setCreator(teacherService.findTeacherById(userId));
        examService.save(exam);
        Question question=new Question();
        model.addAttribute("coursePlan",coursePlan);
        model.addAttribute("question",question);
        model.addAttribute("examId",exam.getId());
        List<CoursePlan> coursePlans=accountService.findCoursePlansByAccountId(userId);
        model.addAttribute("courseplans",coursePlans);
        model.addAttribute("user",exam.getCreator());
        return "addquestion";
    }

    @GetMapping("/make-exam/{course_id}/{user_id}")
    public String makeExam(@PathVariable("course_id") Long course_id, Model model,@PathVariable("user_id") Long user_id){
        CoursePlan coursePlans = coursePlanService.findCoursePlanByID(course_id);
        Teacher user=teacherService.findTeacherById(user_id);
        Exam exam = new Exam();
        exam.setCreator(user);
        model.addAttribute("exam",exam);
        model.addAttribute("coursePlan",coursePlans);
        Account account=accountService.findByID(user_id);
        List<CoursePlan> coursePlanss=accountService.findCoursePlansByAccountId(user_id);
        model.addAttribute("courseplans",coursePlanss);
        model.addAttribute("user",user);
        return "make-exam";
    }

    @GetMapping("show-questionform/{exam_id}")
    public String showQuestionForm(@PathVariable("exam_id") long exam_id,Model model){
        Question question=new Question();
        Exam exam=examService.findExamById(exam_id);
        model.addAttribute("coursePlan",exam.getCoursePlan());
        model.addAttribute("question",question);
        model.addAttribute("examId",exam.getId());
        List<CoursePlan> coursePlans=accountService.findCoursePlansByAccountId(exam.getCreator().getId());
        model.addAttribute("courseplans",coursePlans);
        model.addAttribute("user",exam.getCreator());
        return "addquestion";
    }

    @PostMapping("add-question/{exam_id}")
    public String addQuestion(@PathVariable("exam_id") Long examId,
            @RequestParam("choice1") String choiceContent1,
            @RequestParam("choice2") String choiceContent2,
            @RequestParam("choice3") String choiceContent3,
            @RequestParam("choice4") String choiceContent4
            ,@RequestParam("correctChoice") String correctChoice
            ,@ModelAttribute Question question,
                              Model model){

        Exam exam=examService.findExamById(examId);
        Choice choice1=new Choice();
        Choice choice2=new Choice();
        Choice choice3=new Choice();
        Choice choice4=new Choice();
        choice1.setText(choiceContent1);
        choice2.setText(choiceContent2);
        choice3.setText(choiceContent3);
        choice4.setText(choiceContent4);
        choice1.setQuestion(question);
        choice2.setQuestion(question);
        choice3.setQuestion(question);
        choice4.setQuestion(question);
        question.setChoices(List.of(choice1,choice2,choice3,choice4));

        if (correctChoice.equals("choice1")){
            question.getChoices().get(0).setCorrect(true);
            question.getChoices().get(1).setCorrect(false);
            question.getChoices().get(2).setCorrect(false);
            question.getChoices().get(3).setCorrect(false);

        }
        else if (correctChoice.equals("choice2")){
            question.getChoices().get(1).setCorrect(true);
            question.getChoices().get(0).setCorrect(false);
            question.getChoices().get(2).setCorrect(false);
            question.getChoices().get(3).setCorrect(false);

        }
        else if (correctChoice.equals("choice3")){
            question.getChoices().get(2).setCorrect(true);
            question.getChoices().get(0).setCorrect(false);
            question.getChoices().get(1).setCorrect(false);
            question.getChoices().get(2).setCorrect(false);

        }
        else if (correctChoice.equals("choice4")){
            question.getChoices().get(3).setCorrect(true);
            question.getChoices().get(0).setCorrect(false);
            question.getChoices().get(1).setCorrect(false);
            question.getChoices().get(2).setCorrect(false);

        }
        questionService.save(question);
        exam.getQuestions().add(question);
        examService.save(exam);
        Long id=exam.getCreator().getId();
        Account account=accountService.findByID(id);
        List<CoursePlan> coursePlans=accountService.findCoursePlansByAccountId(id);
        model.addAttribute("user",account);
        model.addAttribute("courseplans",coursePlans);
        return "redirect:/show-exams/"+id;
    }

    @GetMapping("show-exams/{user_id}")
    public String showExams(@PathVariable("user_id") Long userId,Model model){
        Teacher user=teacherService.findTeacherById(userId);
        List<Exam> exams=examService.findExamsByCreator(user);
        model.addAttribute("exams",exams);
        model.addAttribute("user",user);
        List<CoursePlan> coursePlans=accountService.findCoursePlansByAccountId(userId);
        model.addAttribute("courseplans",coursePlans);
        return "showExams";
    }

    @GetMapping("seeExamResult/{user_id}/{exam_id}")
    public String findExamResultsForTeacher(@PathVariable("user_id") Long user_id,
                                            @PathVariable("exam_id") Long exam_id,Model model){

        Account account=accountService.findByID(user_id);
        Exam exam=examService.findExamById(exam_id);
        model.addAttribute("user",account);
        model.addAttribute("courseplan",exam.getCoursePlan());
        model.addAttribute("examScores",exam.getCoursePlan().getExams());
        model.addAttribute("courseplans",accountService.findCoursePlansByAccountId(user_id));
        if (account instanceof Teacher){
//            List<ExamPlan> examPlans=examPlanRepository.findAllByExam(exam);
            model.addAttribute("exam",exam);
            return "teacherResults";
        }
        else{
            return "error500";
        }

    }
}
