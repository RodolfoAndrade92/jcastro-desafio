package com.test.dev.contact.manager.application.service;

import com.test.dev.contact.manager.application.dto.ContactResponse;

import java.util.List;

public interface FindAllContactService {
    List<ContactResponse> findAllContacts();
}
