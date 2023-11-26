package com.m2olie.userDataService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;
@Data
@Embeddable
public class Period {

        @Column(name = "\"start\"")
        private LocalDate start; // start date

        @Column(name = "\"end\"")
        private LocalDate end; // end date
}
