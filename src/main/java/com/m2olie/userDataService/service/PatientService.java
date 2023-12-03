package com.m2olie.userDataService.service;

import com.m2olie.userDataService.model.ContactPoint;
import com.m2olie.userDataService.model.HumanName;
import com.m2olie.userDataService.model.Patient;
import com.m2olie.userDataService.model.Practitioner;
import com.m2olie.userDataService.repository.HumanNameRepository;
import com.m2olie.userDataService.repository.PatientRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final HumanNameService humanNameService;

    @Autowired
    public PatientService(PatientRepository patientRepository, HumanNameService humanNameService) {
        this.patientRepository = patientRepository;
        this.humanNameService = humanNameService;
    }

    public List<Patient> findAll() { return patientRepository.findAll();}

    public Optional<Patient> findById(Long id) { return patientRepository.findById(id);}

    public Patient saveOrUpdate(Patient patient) { return patientRepository.save(patient);}

    public Patient create(Patient patient, String jwtToken) {
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
        humanName.setPatient(patient); // Verknüpfen mit Patient

        // Speichern des HumanName-Objekts
        humanNameService.saveOrUpdateHumanName(humanName);

        ContactPoint contactPoint = new ContactPoint();
        contactPoint.setSystem(ContactPoint.ContactPointSystem.EMAIL);
        contactPoint.setValue(email);

        patient.setNames(Collections.singletonList(humanName));
        patient.setContactPoints(Collections.singletonList(contactPoint));

        return patientRepository.save(patient);
    }

    public void delete(Long id) { patientRepository.deleteById(id);}

    public Patient addContactPoint(Long patientId, ContactPoint newContactPoint) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));
        patient.getContactPoints().add(newContactPoint);
        return patientRepository.save(patient);
    }

    public Patient updateContactPoint(Long patientId, int contactPointIndex, ContactPoint updatedContactPoint) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));

        if (contactPointIndex >= 0 && contactPointIndex < patient.getContactPoints().size()) {
            patient.getContactPoints().set(contactPointIndex, updatedContactPoint);
        } else {
            throw new IllegalArgumentException("Invalid ContactPoint index");
        }

        return patientRepository.save(patient);
    }

    public Patient removeContactPoint(Long patientId, int contactPointIndex) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));

        if (contactPointIndex >= 0 && contactPointIndex < patient.getContactPoints().size()) {
            patient.getContactPoints().remove(contactPointIndex);
        } else {
            throw new IllegalArgumentException("Invalid ContactPoint index");
        }

        return patientRepository.save(patient);
    }

    public Patient updateActiveStatus(Long patientId, boolean newActiveStatus) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));
        patient.setActive(newActiveStatus);
        return patientRepository.save(patient);
    }
}
