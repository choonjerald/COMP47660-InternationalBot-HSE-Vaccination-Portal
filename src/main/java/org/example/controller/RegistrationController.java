package org.example.controller;

import org.example.dto.UserRegistrationDto;
import org.example.exception.EmailNotFoundException;
import org.example.exception.UserAlreadyExistException;
import org.example.model.UserActivity;
import org.example.repository.UserActivityRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserActivityRepository userActivityRepository;

    public RegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@Validated(UserRegistrationDto.MySequence.class) @ModelAttribute("user") UserRegistrationDto registrationDto, BindingResult result) {

        if (result.hasErrors()) {
            return showRegistrationForm();
        }
        try {
            userService.save(registrationDto);

            String activityMessage = "Account created.";
            userActivityRepository.save(new UserActivity(LocalDateTime.now(), activityMessage, userRepository.findByEmail(registrationDto.getEmail())));
        } catch (UserAlreadyExistException | EmailNotFoundException e) {
            result.rejectValue("email", "userData.email", "An account already exists for this email.");
            return showRegistrationForm();
        }
        return "redirect:/login";
    }
}
