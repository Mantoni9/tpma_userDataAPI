package com.m2olie.userDataService.service;

import com.m2olie.userDataService.model.Address;
import com.m2olie.userDataService.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    // Speichern oder Aktualisieren einer Adresse
    public Address saveOrUpdateAddress(Address address) {
        return addressRepository.save(address);
    }

    // Abrufen aller Adressen
    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    // Abrufen einer Adresse anhand ihrer ID
    public Optional<Address> findAddressById(Long id) {
        return addressRepository.findById(id);
    }

    // Löschen einer Adresse
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
