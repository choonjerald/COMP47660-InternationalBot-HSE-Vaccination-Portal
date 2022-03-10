package org.example.controller;


import org.example.dto.UserRegistrationDto;
import org.example.exception.AppointmentNotFoundException;
import org.example.exception.EmailNotFoundException;
import org.example.exception.UserAlreadyExistException;
import org.example.exception.UserNotFoundException;
import org.example.model.Appointment;
import org.example.model.User;
import org.example.model.UserActivity;
import org.example.model.VaccinationCentre;
import org.example.repository.AppointmentRepository;
import org.example.repository.UserActivityRepository;
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


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    UserActivityRepository userActivityRepository;

    @Autowired
    VaccinationCentreRepository vaccinationCentreRepository;

    @RequestMapping({"/selectVaccinationCentre"})
    public String selectVaccinationCentre(Model model) throws EmailNotFoundException {
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

                if(user.getAppointments().isEmpty() && user.getSecondVaccine() == null){
                    List<VaccinationCentre> listVaccinationCentres = vaccinationCentreRepository.findAll();
                    model.addAttribute("listVaccinationCentres", listVaccinationCentres);
                    target = "selectVaccinationCentre";
                } else target = "redirect:/";

            } else {
                String userEmail = principal.toString();
            }
        }
        return target;
    }

    @RequestMapping("/selectAppointmentTime/{id}")
    public String selectAppointmentTime(@PathVariable(value = "id") Long vaccinationCentreId, Model model) throws AppointmentNotFoundException, EmailNotFoundException {
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

                if(user.getAppointments().isEmpty() && user.getSecondVaccine() == null){
                    List<Appointment> listAppointments = appointmentRepository.findAvailableByVaccinationCentre(vaccinationCentreId);
                    model.addAttribute("listAppointments", listAppointments);
                    target = "selectAppointmentTime";
                } else target = "redirect:/";

            } else {
                String userEmail = principal.toString();
            }
        }
        return target;
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
                String userEmail = ((UserDetails)principal).getUsername();
                User user = userRepository.findByEmail(userEmail);

                Appointment appointment = appointmentRepository.findById(appointmentId)
                        .orElseThrow(() -> new AppointmentNotFoundException());
                if(!appointment.isBooked() && user.getAppointments().isEmpty() && user.getSecondVaccine() == null){
                    appointment.setBooked(true);
                    appointment.setUser(user);
                    appointmentRepository.save(appointment);

                    String activityMessage = "Appointment " + appointment.getDate() + " " + appointment.getTime() + " at " + appointment.getVaccinationCentre().getName() + " was booked.";
                    userActivityRepository.save(new UserActivity(LocalDateTime.now(), activityMessage, user));
                }

                model.addAttribute("user", user);
            } else {
                String userEmail = principal.toString();
            }
            target = "redirect:/";

        }
        return target;
    }

    @RequestMapping("/cancelAppointment/{id}")
    public String cancelAppointment(@PathVariable(value = "id") Long appointmentId, Model model) throws AppointmentNotFoundException, EmailNotFoundException {
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

                Appointment appointment = appointmentRepository.findById(appointmentId)
                        .orElseThrow(() -> new AppointmentNotFoundException());

                if(appointment.isBooked() && appointment.getUser() == user){
                    appointment.setBooked(false);
                    appointment.setUser(null);
                    appointmentRepository.save(appointment);

                    String activityMessage = "Appointment " + appointment.getDate() + " " + appointment.getTime() + " at " + appointment.getVaccinationCentre().getName() + " was cancelled.";
                    userActivityRepository.save(new UserActivity(LocalDateTime.now(), activityMessage, user));
                }

                model.addAttribute("user", user);
            } else {
                String userEmail = principal.toString();
            }
            target = "home";

        }

        return "redirect:/";

    }

    @RequestMapping("/viewAppointment/{id}")
    public String viewAppointmentT(@PathVariable(value = "id") Long appointmentId, Model model) throws UserNotFoundException, AppointmentNotFoundException {
        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String target = "welcome";
        if(role.contains("ADMIN")) {
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new AppointmentNotFoundException());
            model.addAttribute("appointment", appointment);

            User user = userRepository.findById(appointment.getUser().getId())
                    .orElseThrow(() -> new UserNotFoundException());
            model.addAttribute("user", user);

            target = "appointmentView";
        }
        return target;
    }

    @RequestMapping("/recordVaccination/{userId}/{vaccine}")
    public String recordVaccination(@PathVariable(value = "userId") Long userId, @PathVariable(value = "vaccine") String vaccine, Model model) throws UserNotFoundException{
        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String target = "welcome";
        if(role.contains("ADMIN")) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException());

            if(user.getFirstVaccine() == null){
                if(vaccine.equals("pfizer")) user.setFirstVaccine("Pfizer");
                else if(vaccine.equals("moderna")) user.setFirstVaccine("Moderna");
                else return "redirect:/login";

                userRepository.save(user);

                Appointment appointment = appointmentRepository.findByUserId(userId);
                LocalDate secondAppointmentDate = appointment.getDate().plusDays(21);
                appointment.setDate(secondAppointmentDate);
                appointment.setAppointmentType("Second Dose");
                appointmentRepository.save(appointment);

                String activityMessage = "First vaccine dose administered of type " + user.getFirstVaccine() + ".";
                userActivityRepository.save(new UserActivity(LocalDateTime.now(), activityMessage, user));

                activityMessage = "Second dose appointment booked at " + appointment.getVaccinationCentre().getName() + " on " + appointment.getDate() + " at " + appointment.getTime() + ".";
                userActivityRepository.save(new UserActivity(LocalDateTime.now(), activityMessage, user));
            } else if(user.getFirstVaccine() != null){
                if(vaccine.equals("pfizer")) user.setSecondVaccine("Pfizer");
                else if(vaccine.equals("moderna")) user.setSecondVaccine("Moderna");
                else return "redirect:/login";

                userRepository.save(user);

                Appointment appointment = appointmentRepository.findByUserId(userId);
                appointmentRepository.delete(appointment);

                String activityMessage = "Second vaccine dose administered of type " + user.getFirstVaccine() + ".";
                userActivityRepository.save(new UserActivity(LocalDateTime.now(), activityMessage, user));

                activityMessage = "Patient is fully vaccinated";
                userActivityRepository.save(new UserActivity(LocalDateTime.now(), activityMessage, user));
            }

            model.addAttribute("user", user);

            target = "redirect:/admin/home";
        }
        return target;
    }
}