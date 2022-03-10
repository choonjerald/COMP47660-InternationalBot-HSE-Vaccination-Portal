package org.example.repository;

import org.example.exception.EmailNotFoundException;
import org.example.model.Appointment;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.isBooked = false AND a.vaccinationCentre.id = :id")
    List<Appointment> findAvailableByVaccinationCentre(Long id);

//    @Modifying
//    @Query("UPDATE Appointment a SET a.isBooked = true, a.user = :user WHERE a.id = :id")
//    void bookAppointmentById(@Param("id") Long id, @Param("user") User user);

}
