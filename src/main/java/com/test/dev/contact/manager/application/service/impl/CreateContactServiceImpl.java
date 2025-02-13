package com.test.dev.contact.manager.application.service.impl;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.CreateContactRequest;
import com.test.dev.contact.manager.application.exception.ContactApiException;
import com.test.dev.contact.manager.application.service.CreateContactService;
import com.test.dev.contact.manager.domain.mapper.ContactMapper;
import com.test.dev.contact.manager.domain.model.Contact;
import com.test.dev.contact.manager.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateContactServiceImpl implements CreateContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public CreateContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactResponse createContact(CreateContactRequest createContactRequest) {
        this.validateContactExistsByCellPhone(createContactRequest.getCellPhone());
        Contact createdContact = contactRepository.save(contactMapper.convertToContact(createContactRequest));
        return contactMapper.convertToContactResponse(createdContact);
    }

    private void validateContactExistsByCellPhone(String cellPhone) {
        if (contactRepository.existsByCellPhone(cellPhone)) {
            throw new ContactApiException("There is already a contact with the cell phone provided: " + cellPhone);
        }
    }
}
