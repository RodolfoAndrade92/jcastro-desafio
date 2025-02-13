package com.test.dev.contact.manager.application.service;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.CreateContactRequest;

public interface CreateContactService {
    ContactResponse createContact(CreateContactRequest createContactRequest);
}
