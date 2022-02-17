package org.example.controller;

import org.example.exception.EmailNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping({"/"})
    public String viewWelcome(Model model) throws EmailNotFoundException {

        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String target = "welcome";
        if(role.contains("USER")) {
            // Get the principal of logged in user
            Object principal = auth.getPrincipal();

            if (principal instanceof UserDetails) {
                String userEmail = ((UserDetails)principal).getUsername();
                User user = userRepository.findByEmail(userEmail);
                model.addAttribute("user", user);
            } else {
                String userEmail = principal.toString();
            }
            target = "home";

        } else if(role.contains("ADMIN")) {
            target = "admin";
        }

        return target;
    }

    @RequestMapping({"/accessDenied"})
    public String accessDenied() {
        return "accessDenied";
    }

}
