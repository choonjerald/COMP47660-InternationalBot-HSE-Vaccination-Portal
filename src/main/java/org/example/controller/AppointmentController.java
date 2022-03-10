package org.example.controller;


import org.example.dto.UserRegistrationDto;
import org.example.exception.AppointmentNotFoundException;
import org.example.exception.EmailNotFoundException;
import org.example.exception.UserAlreadyExistException;
import org.example.model.Appointment;
import org.example.model.User;
import org.example.model.VaccinationCentre;
import org.example.repository.AppointmentRepository;
import org.example.repository.UserRepository;
import org.example.repository.VaccinationCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VaccinationCentreRepository vaccinationCentreRepository;

    @RequestMapping({"/selectVaccinationCentre"})
    public String selectVaccinationCentre(Model model) {
        List<VaccinationCentre> listVaccinationCentres = vaccinationCentreRepository.findAll();
        model.addAttribute("listVaccinationCentres", listVaccinationCentres);
        return "selectVaccinationCentre";
    }

    @RequestMapping("/selectAppointmentTime/{id}")
    public String selectAppointmentTime(@PathVariable(value = "id") Long vaccinationCentreId, Model model) {
        List<Appointment> listAppointments = appointmentRepository.findAvailableByVaccinationCentre(vaccinationCentreId);
        model.addAttribute("listAppointments", listAppointments);
        return "selectAppointmentTime";
    }

    @RequestMapping("/bookAppointment/{id}")
    public String bookAppointment(@PathVariable(value = "id") Long appointmentId, Model model) throws EmailNotFoundException, AppointmentNotFoundException {

        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String target = "welcome";
        if(role.contains("USER")) {
            // Get the principal of logged in user
            Object principal = auth.getPrincipal();

            if (principal instanceof UserDetails) {
                System.out.println("BOOKED");
                String userEmail = ((UserDetails)principal).getUsername();
                User user = userRepository.findByEmail(userEmail);
                Appointment appointment = appointmentRepository.getById(appointmentId);
                appointment.setBooked(true);
                appointment.setUser(user);
                appointmentRepository.saveAndFlush(appointment);
                model.addAttribute("user", user);
            } else {
                String userEmail = principal.toString();
            }
            target = "home";

        }

        return target;
    }



}