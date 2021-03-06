package org.example.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue()
    private Long id;

    private LocalDate date;

    private LocalTime time;

    private Boolean isBooked;

    private String appointmentType;

    @ManyToOne()
    @JoinColumn(name = "vaccinationCentre")
    private VaccinationCentre vaccinationCentre;

    @ManyToOne()
    @JoinColumn(name = "user")
    private User user;

    public Appointment() {
        super();
    }

    public Appointment(Long id, LocalDate date, LocalTime time, Boolean isBooked, String appointmentType) {
        super();
        this.id = id;
        this.date = date;
        this.time = time;
        this.isBooked = isBooked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Boolean isBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }

    public VaccinationCentre getVaccinationCentre() {
        return vaccinationCentre;
    }

    public void setVaccinationCentre(VaccinationCentre vaccinationCentre) {
        this.vaccinationCentre = vaccinationCentre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }
}