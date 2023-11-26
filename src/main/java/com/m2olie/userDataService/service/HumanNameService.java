package com.m2olie.userDataService.service;

import com.m2olie.userDataService.model.HumanName;
import com.m2olie.userDataService.repository.HumanNameRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HumanNameService {

    private final HumanNameRepository humanNameRepository;

    @Autowired
    public HumanNameService(HumanNameRepository humanNameRepository) {
        this.humanNameRepository = humanNameRepository;
    }

    // Speichern oder Aktualisieren eines HumanName
    public HumanName saveOrUpdateHumanName(HumanName humanName) {
        return humanNameRepository.save(humanName);
    }

    // Abrufen aller HumanNames
    public List<HumanName> findAllHumanNames() {
        return humanNameRepository.findAll();
    }

    // Abrufen eines HumanName anhand seiner ID
    public Optional<HumanName> findHumanNameById(Long id) {
        return humanNameRepository.findById(id);
    }

    // Löschen eines HumanName
    public void deleteHumanName(Long id) {
        humanNameRepository.deleteById(id);
    }

}
