package org.example.controller;

import org.example.exception.EmailNotFoundException;
import org.example.exception.QuestionNotFoundException;
import org.example.model.Question;
import org.example.model.User;
import org.example.model.UserActivity;
import org.example.repository.QuestionRepository;
import org.example.repository.UserActivityRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/faq")
public class ForumController {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserActivityRepository userActivityRepository;

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

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String role = auth.getAuthorities().toString();

            if (role.contains("USER")) {
                // Get the principal of logged in user
                Object principal = auth.getPrincipal();

                if (principal instanceof UserDetails) {
                    String userEmail = ((UserDetails) principal).getUsername();
                    User user = userRepository.findByEmail(userEmail);

                    //log user activity
                    String activityMessage = "Posted question with title \"" + postedQuestion.getTitle() + "\" in forum.";
                    userActivityRepository.save(new UserActivity(LocalDateTime.now(), activityMessage, user));
                }
            }

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
