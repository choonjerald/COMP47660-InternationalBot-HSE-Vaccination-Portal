package org.example.controller;

import org.example.dto.UserRegistrationDto;
import org.example.exception.UserAlreadyExistException;
import org.example.model.User;
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

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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
        }catch (UserAlreadyExistException e){
            result.rejectValue("email", "userData.email","An account already exists for this email.");
            return showRegistrationForm();
        }
        return "redirect:/login";
    }
}
