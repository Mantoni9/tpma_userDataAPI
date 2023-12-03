package com.m2olie.userDataService.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Patient {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active;

    @JsonManagedReference
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HumanName> names;

    @ElementCollection
    @CollectionTable(name = "patient_contact_point", joinColumns = @JoinColumn(name = "patient_id"))
    private List<ContactPoint> contactPoints;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birthDate;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
    private Boolean maritalStatus;

    @JsonManagedReference
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;

    @ManyToMany
    @JoinTable(
            name = "patient_language",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "language_code"))
    private Set<Language> languageSet;

}
