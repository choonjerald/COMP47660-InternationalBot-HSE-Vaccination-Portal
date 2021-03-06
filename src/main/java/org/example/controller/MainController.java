package org.example.controller;

import org.example.exception.EmailNotFoundException;
import org.example.model.Appointment;
import org.example.model.User;
import org.example.repository.AppointmentRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @RequestMapping({"/"})
    public String viewWelcome(Model model) throws EmailNotFoundException {

        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String target = "welcome";
        if (role.contains("USER")) {
            // Get the principal of logged in user
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                String userEmail = ((UserDetails) principal).getUsername();
                User user = userRepository.findByEmail(userEmail);
                model.addAttribute("user", user);
            } else {
                String userEmail = principal.toString();
            }
            target = "redirect:/user/home";

        } else if (role.contains("ADMIN")) {
            List<Appointment> appointments = appointmentRepository.findAllBookedAppointments();
            model.addAttribute("appointments", appointments);
            target = "redirect:/admin/home";
        }

        return target;
    }

    @RequestMapping({"/accessDenied"})
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/statistic")
    public String getAggregatedData(Model model) throws EmailNotFoundException {
        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        model.addAttribute("role", role);

        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        model.addAttribute("mostCommonNationality", userRepository.getMostCommonNationality().get(0).split(",")[0]);
        model.addAttribute("mostCommonAgeGroup", userRepository.getMostCommonAgeGroup().get(0).split(",")[0]);
        model.addAttribute("registeredMales", userRepository.getGenderCounts().get(0).split(",")[1]);
        model.addAttribute("registeredFemales", getGenderCount(userRepository.getGenderCounts()));
        model.addAttribute("firstVaccinePfizerCount", userRepository.getFirstVaccineCount("Pfizer"));
        model.addAttribute("secondVaccinePfizerCount", userRepository.getSecondVaccineCount("Pfizer"));
        model.addAttribute("firstVaccineModernaCount", userRepository.getFirstVaccineCount("Moderna"));
        model.addAttribute("secondVaccineModernaCount", userRepository.getSecondVaccineCount("Moderna"));
        return "/statistic";
    }

    public String getGenderCount(List<String> user) {
        if (user.size() > 1) {
            try {
                return user.get(1).split(",")[1];
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
            }
        }
        return "0";
    }

}
