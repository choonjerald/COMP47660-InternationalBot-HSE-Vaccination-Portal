package org.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exception.QuestionNotFoundException;
import org.example.model.Appointment;
import org.example.model.Question;
import org.example.repository.AppointmentRepository;
import org.example.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LogManager.getLogger(AdminController.class);
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping({"/home"})
    public String viewHomePage(Model model) {
        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        if (role.contains("ADMIN")) {
            List<Appointment> appointments = appointmentRepository.findAllBookedAppointments();
            model.addAttribute("appointments", appointments);
            List<Question> questionList = questionRepository.findAll();
            model.addAttribute("adminQList", questionList);
            logger.info("Admin accessed by: " + auth.getName() + " IP: " + getClientIP());
        }

        return "admin";
    }

    @GetMapping("/faq/edit/{id}")
    public String getAdminEditForum(@PathVariable(value = "id") Long questionId, Model model) throws QuestionNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        if (role.contains("ADMIN")) {
            Question question = questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException());
            model.addAttribute("displayQuestion", question);
        }
        return "editQuestionForum";
    }

    @RequestMapping(value = "/faq/edit/{id}", method = RequestMethod.POST)
    public String registerAdminAnswer(@Validated @ModelAttribute("displayQuestion") Question postedQuestion, BindingResult result, Model model) throws QuestionNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        if (role.contains("ADMIN")) {
            if (result.hasErrors()) {
                return getAdminEditForum(postedQuestion.getId(), model);
            }
            try {
                postedQuestion.setStatus(true);
                questionRepository.save(postedQuestion);
            } catch (Exception e) {
                return getAdminEditForum(postedQuestion.getId(), model);
            }
        }

        return "redirect:/admin/home";
    }

    public String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
