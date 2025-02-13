package com.test.dev.contact.manager.infrastructure.controller.impl;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.CreateContactRequest;
import com.test.dev.contact.manager.application.dto.UpdateContactRequest;
import com.test.dev.contact.manager.application.service.CreateContactService;
import com.test.dev.contact.manager.domain.mapper.ContactMapper;
import com.test.dev.contact.manager.infrastructure.controller.ContactController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class ContactControllerImpl implements ContactController {

    private final CreateContactService createContactService;

    public ContactControllerImpl(ContactMapper contactMapper, CreateContactService createContactService) {
        this.createContactService = createContactService;
    }

    @Override
    public ResponseEntity<ContactResponse> create(CreateContactRequest createContactRequest) {
        ContactResponse contactResponse = createContactService.createContact(createContactRequest);
        return ResponseEntity.created(URI.create("contacts/create")).body(contactResponse);
    }

    @Override
    public ResponseEntity<List<ContactResponse>> get() {
        return null;
    }

    @Override
    public ResponseEntity<ContactResponse> update(UpdateContactRequest updateContactRequest) {
        return null;
    }
}
