package com.m2olie.userDataService.controller;

import com.m2olie.userDataService.model.Practitioner;
import com.m2olie.userDataService.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/practitioners")
public class    PractitionerController {
    private final PractitionerService practitionerService;

    @Autowired
    public PractitionerController(PractitionerService practitionerService) {
        this.practitionerService = practitionerService;
    }

    @GetMapping
    public ResponseEntity<List<Practitioner>> getAllPractitioners() {
        return ResponseEntity.ok(practitionerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Practitioner> getPractitionerById(@PathVariable Long id) {
        return practitionerService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Practitioner> createPractitioner(@RequestBody Practitioner practitioner,
                                                           @RequestHeader("Authorization") String authToken) {
        String token = authToken.startsWith("Bearer ") ? authToken.substring(7) : authToken;

        Practitioner savedPractitioner = practitionerService.create(practitioner, token);
        return ResponseEntity.ok(savedPractitioner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Practitioner> updatePractitioner(@PathVariable Long id, @RequestBody Practitioner practitioner) {
        if (!id.equals(practitioner.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Practitioner updatedPractitioner = practitionerService.saveOrUpdate(practitioner);
        return ResponseEntity.ok(updatedPractitioner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePractitioner(@PathVariable Long id) {
        practitionerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
