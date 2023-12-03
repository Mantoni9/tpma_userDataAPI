package com.m2olie.userDataService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

/**
 * Represents a language with ISO 639-1 language code and its name.
 * This entity is used to store language information in the database.
 * Each language is identified by its ISO 639-1 code which is a two-letter
 * lowercase language code.
 */
@Entity
@Data
public class Language {

    @Id
    private String code; //ISO 639-1 language code

    private String name; // The name of the language

    @ManyToMany(mappedBy = "languageSet")
    private Set<Patient> patientSet;
}
