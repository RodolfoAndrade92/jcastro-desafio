package com.test.dev.contact.manager.application.service.impl;

import com.test.dev.contact.manager.application.exception.ContactApiException;
import com.test.dev.contact.manager.application.service.ContactValidationService;
import com.test.dev.contact.manager.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactValidationServiceImpl implements ContactValidationService {

    private final ContactRepository contactRepository;

    public ContactValidationServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void validateContactExistsByCellPhone(String cellPhone) {
        if (contactRepository.existsByCellPhone(cellPhone)) {
            throw new ContactApiException("There is already a contact with the cell phone provided: " + cellPhone);
        }
    }
}
