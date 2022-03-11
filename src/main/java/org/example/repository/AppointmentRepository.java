package org.example.repository;

import org.example.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.isBooked = false AND a.vaccinationCentre.id = :id")
    List<Appointment> findAvailableByVaccinationCentre(Long id);

    @Query("SELECT a FROM Appointment a WHERE a.isBooked = true")
    List<Appointment> findAllBookedAppointments();

    @Query("SELECT a FROM Appointment a WHERE a.isBooked = true AND a.user.id = :id")
    Appointment findByUserId(Long id);
}
