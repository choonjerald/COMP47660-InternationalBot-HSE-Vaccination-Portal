package org.example.controller;

import org.example.exception.EmailNotFoundException;
import org.example.model.Appointment;
import org.example.model.User;
import org.example.repository.AppointmentRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @RequestMapping({"/home"})
    public String viewHomePage(Model model) throws EmailNotFoundException {

        // Get the principal of logged in user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String userEmail = ((UserDetails)principal).getUsername();
            User user = userRepository.findByEmail(userEmail);
            model.addAttribute("user", user);
        } else {
            String userEmail = principal.toString();
        }

        return "home";
    }

}
