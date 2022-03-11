package org.example.controller;

import org.example.model.Appointment;
import org.example.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AppointmentRepository appointmentRepository;

    @RequestMapping({"/home"})
    public String viewHomePage(Model model) {
        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        if (role.contains("ADMIN")) {
            List<Appointment> appointments = appointmentRepository.findAllBookedAppointments();
            model.addAttribute("appointments", appointments);
        }

        return "admin";
    }

    @GetMapping({"/forum"})
    public String getAdminForumPage() { return "adminForum"; }

}
