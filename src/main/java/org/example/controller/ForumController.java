package org.example.controller;

import org.example.exception.QuestionNotFoundException;
import org.example.model.Question;
import org.example.model.User;
import org.example.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/faq")
public class ForumController {
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/create")
    public String getForumPage() { return "forum"; }

    @PostMapping("/create")
    public String registerUserAccount(@Validated @ModelAttribute("question") Question postedQuestion, BindingResult result) {

        if (result.hasErrors()) {
            return getForumPage();
        }
        try {
            postedQuestion.setStatus(false);
            questionRepository.save(postedQuestion);
        }catch (Exception e){
            return getForumPage();
        }
        return "redirect:/faq";
    }

    @GetMapping()
    public String getQuestionsList(Model model) {
        List<Question> questionList = questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "faq";
    }

    @GetMapping("/view/{id}")
    public String getNoteById(@PathVariable(value = "id") Long questionId, Model model) throws QuestionNotFoundException {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException());
        model.addAttribute("displayQuestion", question);
        return "detailedQuestion";
    }

}
