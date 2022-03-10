package org.example.repository;

import org.example.exception.EmailNotFoundException;
import org.example.model.User;
import org.example.model.VaccinationCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCentreRepository extends JpaRepository<VaccinationCentre, Long> {
}
