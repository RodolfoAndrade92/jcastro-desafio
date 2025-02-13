package com.test.dev.contact.manager.application.service.impl;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.UpdateContactRequest;
import com.test.dev.contact.manager.application.service.ContactValidationService;
import com.test.dev.contact.manager.application.service.UpdateContactService;
import com.test.dev.contact.manager.domain.mapper.ContactMapper;
import com.test.dev.contact.manager.domain.model.Contact;
import com.test.dev.contact.manager.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateContactServiceImpl implements UpdateContactService {

    private final ContactValidationService contactValidationService;
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public UpdateContactServiceImpl(ContactValidationService contactValidationService,
                                    ContactRepository contactRepository,
                                    ContactMapper contactMapper) {
        this.contactValidationService = contactValidationService;
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactResponse updateContact(UpdateContactRequest updateContactRequest) {
        contactValidationService.validateContactExistsByCellPhone(updateContactRequest.getCellPhone());
        Contact contactFound = contactRepository.findById(updateContactRequest.getId());
        Contact contactToUpdate = contactMapper.convertToContact(updateContactRequest, contactFound);
        Contact updatedContact = contactRepository.save(contactToUpdate);
        return contactMapper.convertToContactResponse(updatedContact);
    }
}
