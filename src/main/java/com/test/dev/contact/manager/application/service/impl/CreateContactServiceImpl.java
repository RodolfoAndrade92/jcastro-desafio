package com.test.dev.contact.manager.application.service.impl;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.CreateContactRequest;
import com.test.dev.contact.manager.application.service.ContactValidationService;
import com.test.dev.contact.manager.application.service.CreateContactService;
import com.test.dev.contact.manager.domain.mapper.ContactMapper;
import com.test.dev.contact.manager.domain.model.Contact;
import com.test.dev.contact.manager.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateContactServiceImpl implements CreateContactService {

    private final ContactValidationService contactValidationService;
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public CreateContactServiceImpl(ContactValidationService contactValidationService,
                                    ContactRepository contactRepository,
                                    ContactMapper contactMapper) {
        this.contactValidationService = contactValidationService;
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactResponse createContact(CreateContactRequest createContactRequest) {
        contactValidationService.validateContactExistsByCellPhone(createContactRequest.getCellPhone());
        Contact createdContact = contactRepository.save(contactMapper.convertToContact(createContactRequest));
        return contactMapper.convertToContactResponse(createdContact);
    }
}
