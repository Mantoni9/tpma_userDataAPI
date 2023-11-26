package com.m2olie.userDataService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Practitioner {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active; // Whether this practitioner's record is in active use

    @OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HumanName> names;

    @ElementCollection
    @CollectionTable(name = "practitioner_contact_point", joinColumns = @JoinColumn(name = "practitioner_id"))
    private List<ContactPoint> telecoms; // Contact details for the practitioner

    @Enumerated(EnumType.STRING)
    private Gender gender; // Gender of the practitioner
    private LocalDate birthDate; // Birth date of the practitioner

    @OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses; // Address(es) of the practitioner

}
