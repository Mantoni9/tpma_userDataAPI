package com.m2olie.userDataService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class HumanName {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "practitioner_id")
    private Practitioner practitioner;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @Enumerated(EnumType.STRING)
    private NameUse use; // enum for the use
    private String text; // full name as text
    private String family; // family name / last name
    @ElementCollection
    @CollectionTable(name = "human_name_given", joinColumns = @JoinColumn(name = "human_name_id"))
    @Column(name = "given")
    private List<String> given; // first name(s), incl. middle name
    @ElementCollection
    @CollectionTable(name = "human_name_prefix", joinColumns = @JoinColumn(name = "human_name_id"))
    @Column(name = "prefix")
    private List<String> prefix; // title, e.x. "Dr.", "Prof."
    @ElementCollection
    @CollectionTable(name = "human_name_suffix", joinColumns = @JoinColumn(name = "human_name_id"))
    @Column(name = "suffix")
    private List<String> suffix; // e.x. "Jr.", "Sr."
    private Period period; // valid time period of name

    public enum NameUse {
        USUAL, OFFICIAL, TEMP, NICKNAME, ANONYMOUS, OLD, MAIDEN
    }
}
