package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "vaccinationCentres")
public class VaccinationCentre {
    @OneToMany(mappedBy = "vaccinationCentre", cascade = CascadeType.ALL)
    Set<Appointment> appointments;
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String name;

    public VaccinationCentre() {
        super();
    }

    public VaccinationCentre(Long id, String vaccination_centre) {
        super();
        this.id = id;
        this.name = vaccination_centre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
