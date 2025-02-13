package com.test.dev.contact.manager.application.service;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.UpdateContactRequest;

public interface UpdateContactService {
    ContactResponse updateContact(UpdateContactRequest updateContactRequest);
}
