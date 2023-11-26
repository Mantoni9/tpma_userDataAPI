package com.m2olie.userDataService.service;

import com.m2olie.userDataService.model.Address;
import com.m2olie.userDataService.model.ContactPoint;
import com.m2olie.userDataService.model.HumanName;
import com.m2olie.userDataService.model.Practitioner;
import com.m2olie.userDataService.repository.PractitionerRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PractitionerService {
    private final PractitionerRepository practitionerRepository;
    private final HumanNameService humanNameService;

    @Autowired
    public PractitionerService(PractitionerRepository practitionerRepository, HumanNameService humanNameService) {
        this.practitionerRepository = practitionerRepository;
        this.humanNameService = humanNameService;
    }

    public List<Practitioner> findAll() {
        return practitionerRepository.findAll();
    }

    public Optional<Practitioner> findById(Long id) {
        return practitionerRepository.findById(id);
    }

    public Practitioner saveOrUpdate(Practitioner practitioner) {
        return practitionerRepository.save(practitioner);
    }

    public Practitioner create(Practitioner practitioner, String jwtToken) {
        String[] chunks = jwtToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        JSONObject jsonObject = new JSONObject(payload);
        String name = jsonObject.optString("family_name"); // oder "name" für den vollständigen Namen
        String givenName = jsonObject.optString("given_name");
        String email = jsonObject.optString("email");

        HumanName humanName = new HumanName();
        humanName.setFamily(name);
        humanName.setGiven(Collections.singletonList(givenName));
        humanName.setPractitioner(practitioner); // Verknüpfen mit Practitioner

        // Speichern des HumanName-Objekts
        humanNameService.saveOrUpdateHumanName(humanName);

        ContactPoint contactPoint = new ContactPoint();
        contactPoint.setSystem(ContactPoint.ContactPointSystem.EMAIL);
        contactPoint.setValue(email);

        practitioner.setNames(Collections.singletonList(humanName));
        practitioner.setTelecoms(Collections.singletonList(contactPoint));

        return practitionerRepository.save(practitioner);
    }

    public void delete(Long id) {
        practitionerRepository.deleteById(id);
    }
/*

    public Practitioner addAddress(Long practitionerId, Address newAddress) {
        Practitioner practitioner = practitionerRepository.findById(practitionerId)
                .orElseThrow(() -> new NoSuchElementException("Practitioner not found"));
        practitioner.getAddresses().add(newAddress);
        return practitionerRepository.save(practitioner);
    }

    public Practitioner updateAddress(Long practitionerId, int addressIndex, Address updatedAddress) {
        Practitioner practitioner = practitionerRepository.findById(practitionerId)
                .orElseThrow(() -> new NoSuchElementException("Practitioner not found"));

        if (addressIndex >= 0 && addressIndex < practitioner.getAddresses().size()) {
            practitioner.getAddresses().set(addressIndex, updatedAddress);
        } else {
            throw new IllegalArgumentException("Invalid address index");
        }

        return practitionerRepository.save(practitioner);
    }

    public Practitioner removeAddress(Long practitionerId, int addressIndex) {
        Practitioner practitioner = practitionerRepository.findById(practitionerId)
                .orElseThrow(() -> new NoSuchElementException("Practitioner not found"));

        if (addressIndex >= 0 && addressIndex < practitioner.getAddresses().size()) {
            practitioner.getAddresses().remove(addressIndex);
        } else {
            throw new IllegalArgumentException("Invalid address index");
        }

        return practitionerRepository.save(practitioner);
    }
*/

    public Practitioner addContactPoint(Long practitionerId, ContactPoint newContactPoint) {
        Practitioner practitioner = practitionerRepository.findById(practitionerId)
                .orElseThrow(() -> new NoSuchElementException("Practitioner not found"));
        practitioner.getTelecoms().add(newContactPoint);
        return practitionerRepository.save(practitioner);
    }

    public Practitioner updateContactPoint(Long practitionerId, int contactPointIndex, ContactPoint updatedContactPoint) {
        Practitioner practitioner = practitionerRepository.findById(practitionerId)
                .orElseThrow(() -> new NoSuchElementException("Practitioner not found"));

        if (contactPointIndex >= 0 && contactPointIndex < practitioner.getTelecoms().size()) {
            practitioner.getTelecoms().set(contactPointIndex, updatedContactPoint);
        } else {
            throw new IllegalArgumentException("Invalid ContactPoint index");
        }

        return practitionerRepository.save(practitioner);
    }

    public Practitioner removeContactPoint(Long practitionerId, int contactPointIndex) {
        Practitioner practitioner = practitionerRepository.findById(practitionerId)
                .orElseThrow(() -> new NoSuchElementException("Practitioner not found"));

        if (contactPointIndex >= 0 && contactPointIndex < practitioner.getTelecoms().size()) {
            practitioner.getTelecoms().remove(contactPointIndex);
        } else {
            throw new IllegalArgumentException("Invalid ContactPoint index");
        }

        return practitionerRepository.save(practitioner);
    }

    public Practitioner updateActiveStatus(Long practitionerId, boolean newActiveStatus) {
        Practitioner practitioner = practitionerRepository.findById(practitionerId)
                .orElseThrow(() -> new NoSuchElementException("Practitioner not found"));
        practitioner.setActive(newActiveStatus);
        return practitionerRepository.save(practitioner);
    }

/*
    public Practitioner addHumanName(Long practitionerId, HumanName newHumanName) {
        Practitioner practitioner = practitionerRepository.findById(practitionerId)
                .orElseThrow(() -> new NoSuchElementException("Practitioner not found"));
        practitioner.getNames().add(newHumanName);
        return practitionerRepository.save(practitioner);
    }

    public Practitioner updateHumanName(Long practitionerId, int nameIndex, HumanName updatedHumanName) {
        Practitioner practitioner = practitionerRepository.findById(practitionerId)
                .orElseThrow(() -> new NoSuchElementException("Practitioner not found"));

        if (nameIndex >= 0 && nameIndex < practitioner.getNames().size()) {
            practitioner.getNames().set(nameIndex, updatedHumanName);
        } else {
            throw new IllegalArgumentException("Invalid human name index");
        }

        return practitionerRepository.save(practitioner);
    }
    @Transactional
    public Practitioner removeHumanName(Long practitionerId, int nameIndex) {
        Practitioner practitioner = practitionerRepository.findById(practitionerId)
                .orElseThrow(() -> new NoSuchElementException("Practitioner not found"));

        if (practitioner.getNames().size() <= 1) {
            throw new IllegalStateException("Cannot remove the last human name");
        }

        if (nameIndex >= 0 && nameIndex < practitioner.getNames().size()) {
            practitioner.getNames().remove(nameIndex);
        } else {
            throw new IllegalArgumentException("Invalid human name index");
        }

        return practitionerRepository.save(practitioner);
    }
*/

}
