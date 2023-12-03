package com.m2olie.userDataService.controller;

import com.m2olie.userDataService.model.Contact;
import com.m2olie.userDataService.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.findAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        return contactService.findContactById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact saveContact = contactService.saveOrUpdate(contact);
        return ResponseEntity.ok(saveContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        if (!id.equals(contact.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Contact updateContact = contactService.saveOrUpdate((contact));
        return ResponseEntity.ok(updateContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.ok().build();
    }
}
