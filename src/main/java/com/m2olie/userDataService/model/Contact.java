package com.m2olie.userDataService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Contact {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private String relationship;

    @JsonManagedReference
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HumanName> names;

    @ElementCollection
    @CollectionTable(name = "contact_contact_point", joinColumns = @JoinColumn(name = "contact_id"))
    private List<ContactPoint> contactPoints;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    private Period period;
}
