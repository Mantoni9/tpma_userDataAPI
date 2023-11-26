package com.m2olie.userDataService.controller;

import com.m2olie.userDataService.model.HumanName;
import com.m2olie.userDataService.service.HumanNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/human-names")
public class HumanNameController {

    private final HumanNameService humanNameService;

    @Autowired
    public HumanNameController(HumanNameService humanNameService) {
        this.humanNameService = humanNameService;
    }

    @PostMapping
    public ResponseEntity<HumanName> createHumanName(@RequestBody HumanName humanName) {
        HumanName savedHumanName = humanNameService.saveOrUpdateHumanName(humanName);
        return ResponseEntity.ok(savedHumanName);
    }

    @GetMapping
    public ResponseEntity<List<HumanName>> getAllHumanNames() {
        return ResponseEntity.ok(humanNameService.findAllHumanNames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HumanName> getHumanNameById(@PathVariable Long id) {
        return humanNameService.findHumanNameById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HumanName> updateHumanName(@PathVariable Long id, @RequestBody HumanName humanName) {
        return humanNameService.findHumanNameById(id)
                .map(existingHumanName -> {
                    humanName.setId(id); // Stellen Sie sicher, dass die ID erhalten bleibt.
                    return ResponseEntity.ok(humanNameService.saveOrUpdateHumanName(humanName));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHumanName(@PathVariable Long id) {
        humanNameService.deleteHumanName(id);
        return ResponseEntity.ok().build();
    }
}
