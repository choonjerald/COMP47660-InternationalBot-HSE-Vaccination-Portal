package org.example.controller;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
//    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//    Validator validator = factory.getValidator();

    @Autowired
    UserRepository userRepository;

    @RequestMapping({"/"})
    public String viewWelcome(Model model){
        return "welcome";
    }

    @RequestMapping({"/home"})
    public String viewHomePage(Model model){
        return "home";
    }

    // Create a User Account
    @RequestMapping("/new")
    public String createUser(){
        return "register";
    }

    // Save Created Account
    @PostMapping("/newAcc")
    public String saveCreatedUser(@Validated(User.MySequence.class) @ModelAttribute("user") User user, BindingResult result, Model model){

        if (result.hasErrors()) {
            return createUser();
        }

        userRepository.save(user);
        return viewHomePage(model);
    }

}
