package com.test.dev.contact.manager.application.service.impl;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.service.FindActiveContactService;
import com.test.dev.contact.manager.domain.mapper.ContactMapper;
import com.test.dev.contact.manager.domain.model.Contact;
import com.test.dev.contact.manager.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindActiveContactServiceImpl implements FindActiveContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public FindActiveContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public List<ContactResponse> findActiveContacts() {
        List<Contact> contactList = contactRepository.findActives();
        return contactMapper.convertToContactResponseList(contactList);
    }
}
