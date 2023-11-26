package com.m2olie.userDataService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "practitioner_id")
    private Practitioner practitioner;
    @Enumerated(EnumType.STRING)
    private AddressUse use; // e.g., home, work, temp, old, billing
    @Enumerated(EnumType.STRING)
    private AddressType type; // e.g., postal, physical, both
    private String text; // Text representation of the address
    @ElementCollection
    @CollectionTable(name = "address_line", joinColumns = @JoinColumn(name = "address_id"))
    @Column(name = "line")
    private List<String> line; // Street name, number, direction & P.O. Box etc.
    private String city; // Name of city, town etc.
    private String district; // District name (aka county)
    private String state; // Sub-unit of country (abbreviations ok)
    private String postalCode; // Postal code for area
    private String country; // Country (e.g., ISO 3166 2 or 3 letter code)
    private Period period; // Time period when address was/is in use

    public enum AddressUse {
        HOME, WORK, TEMP, OLD, BILLING
    }

    public enum AddressType {
        POSTAL, PHYSICAL, BOTH
    }
}
