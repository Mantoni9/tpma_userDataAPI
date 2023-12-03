package com.m2olie.userDataService.controller;

import com.m2olie.userDataService.model.Patient;
import com.m2olie.userDataService.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientByUd(@PathVariable Long id) {
        return patientService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient, @RequestHeader("Authorization") String authToken) {
        String token = authToken.startsWith("Bearer ") ? authToken.substring(7) : authToken;

        Patient savePatient = patientService.create(patient, token);
        return ResponseEntity.ok(savePatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        if (!id.equals(patient.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Patient updatePatient = patientService.saveOrUpdate(patient);
        return ResponseEntity.ok(updatePatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.ok().build();
    }

}
