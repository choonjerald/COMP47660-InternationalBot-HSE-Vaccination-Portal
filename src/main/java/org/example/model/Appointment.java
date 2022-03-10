package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue()
    private Long id;
   // @NotBlank
    private LocalDate date;
    //@NotBlank
    private LocalTime time;
    //@NotBlank
    private Boolean isBooked;

    @ManyToOne()
    @JoinColumn(name = "vaccinationCentre")
    private VaccinationCentre vaccinationCentre;

    @ManyToOne()
    @JoinColumn(name = "user")
    private User user;

    public Appointment(){
        super();
    }

    public Appointment(Long id, LocalDate date, LocalTime time, Boolean isBooked) {
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

    public Boolean getBooked() {
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
}