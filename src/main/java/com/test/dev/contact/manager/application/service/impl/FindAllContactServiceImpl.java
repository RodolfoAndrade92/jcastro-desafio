package com.test.dev.contact.manager.application.service.impl;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.service.FindAllContactService;
import com.test.dev.contact.manager.domain.mapper.ContactMapper;
import com.test.dev.contact.manager.domain.model.Contact;
import com.test.dev.contact.manager.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllContactServiceImpl implements FindAllContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public FindAllContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public List<ContactResponse> findAllContacts() {
        List<Contact> contactList = contactRepository.findAll();
        return contactMapper.convertToContactResponseList(contactList);
    }
}
