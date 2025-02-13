package com.test.dev.contact.manager.infrastructure.controller.impl;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.CreateContactRequest;
import com.test.dev.contact.manager.application.dto.UpdateContactRequest;
import com.test.dev.contact.manager.application.service.CreateContactService;
import com.test.dev.contact.manager.application.service.FindActiveContactService;
import com.test.dev.contact.manager.application.service.FindAllContactService;
import com.test.dev.contact.manager.application.service.UpdateContactService;
import com.test.dev.contact.manager.infrastructure.controller.ContactController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class ContactControllerImpl implements ContactController {

    private final CreateContactService createContactService;
    private final FindAllContactService findAllContactService;
    private final FindActiveContactService findActiveContactService;
    private final UpdateContactService updateContactService;

    public ContactControllerImpl(CreateContactService createContactService,
                                 FindAllContactService findAllContactService,
                                 FindActiveContactService findActiveContactService,
                                 UpdateContactService updateContactService) {
        this.createContactService = createContactService;
        this.findAllContactService = findAllContactService;
        this.findActiveContactService = findActiveContactService;
        this.updateContactService = updateContactService;
    }

    @Override
    public ResponseEntity<ContactResponse> create(CreateContactRequest createContactRequest) {
        ContactResponse contactResponse = createContactService.createContact(createContactRequest);
        return ResponseEntity.created(URI.create("contacts/create")).body(contactResponse);
    }

    @Override
    public ResponseEntity<List<ContactResponse>> getAll() {
        List<ContactResponse> contactResponses = findAllContactService.findAllContacts();
        return ResponseEntity.ok(contactResponses);
    }

    @Override
    public ResponseEntity<List<ContactResponse>> getActive() {
        List<ContactResponse> contactResponses = findActiveContactService.findActiveContacts();
        return ResponseEntity.ok(contactResponses);
    }

    @Override
    public ResponseEntity<ContactResponse> update(UpdateContactRequest updateContactRequest) {
        ContactResponse contactResponse = updateContactService.updateContact(updateContactRequest);
        return ResponseEntity.ok(contactResponse);
    }
}
