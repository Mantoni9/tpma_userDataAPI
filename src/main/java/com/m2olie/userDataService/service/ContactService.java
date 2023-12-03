package com.m2olie.userDataService.service;

import com.m2olie.userDataService.model.Contact;
import com.m2olie.userDataService.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> findContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact saveOrUpdate(Contact contact) {
        return contactRepository.save(contact);
    }

    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
