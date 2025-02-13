package com.test.dev.contact.manager.application.service.impl;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.UpdateContactRequest;
import com.test.dev.contact.manager.application.exception.ContactApiException;
import com.test.dev.contact.manager.application.service.ContactValidationService;
import com.test.dev.contact.manager.domain.mapper.ContactMapper;
import com.test.dev.contact.manager.domain.model.Contact;
import com.test.dev.contact.manager.domain.repository.ContactRepository;
import com.test.dev.contact.manager.util.BuilderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Update contact service tests")
public class UpdateContactServiceImplTest {

    @Mock
    private ContactValidationService contactValidationService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactMapper contactMapper;

    @InjectMocks
    private UpdateContactServiceImpl updateContactService;

    private UpdateContactRequest updateContactRequest;
    private Contact contactFound;
    private Contact contactToUpdate;
    private Contact updatedContact;
    private ContactResponse contactResponse;

    @BeforeEach
    public void setUp() {
        updateContactRequest = BuilderUtil.buildUpdateContactRequest();
        contactFound = BuilderUtil.buildContact();
        contactToUpdate = BuilderUtil.buildContact();
        updatedContact = BuilderUtil.buildContact();
        contactResponse = BuilderUtil.buildContactResponse();
    }

    @Nested
    @DisplayName("When to update a contact")
    class WhenUpdatingContact {

        @Test
        @DisplayName("Should validate the cell phone, search for the contact, update and return the updated contact")
        void testUpdateContact_ShouldValidateFindUpdateAndReturnContact() {
            when(contactRepository.findById(updateContactRequest.getId())).thenReturn(contactFound);
            when(contactMapper.convertToContact(updateContactRequest, contactFound)).thenReturn(contactToUpdate);
            when(contactRepository.save(contactToUpdate)).thenReturn(updatedContact);
            when(contactMapper.convertToContactResponse(updatedContact)).thenReturn(contactResponse);

            ContactResponse result = updateContactService.updateContact(updateContactRequest);

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertEquals(contactResponse.getId(), result.getId()),
                    () -> assertEquals(contactResponse.getCellPhone(), result.getCellPhone()),

                    () -> verify(contactValidationService, times(1))
                            .validateContactExistsByCellPhone(updateContactRequest.getCellPhone()),
                    () -> verify(contactRepository, times(1))
                            .findById(updateContactRequest.getId()),
                    () -> verify(contactMapper, times(1))
                            .convertToContact(updateContactRequest, contactFound),
                    () -> verify(contactRepository, times(1))
                            .save(contactToUpdate),
                    () -> verify(contactMapper, times(1))
                            .convertToContactResponse(updatedContact)
            );
        }

        @Test
        @DisplayName("Should throw exception if the phone is already in use by another contact")
        void testUpdateContact_WhenCellPhoneExists_ShouldThrowException() {
            doThrow(new ContactApiException("Cell phone already in use")).when(contactValidationService)
                    .validateContactExistsByCellPhone(updateContactRequest.getCellPhone());

            ContactApiException exception = assertThrows(ContactApiException.class,
                    () -> updateContactService.updateContact(updateContactRequest));

            assertAll(
                    () -> assertEquals("Cell phone already in use", exception.getMessage()),

                    () -> verify(contactValidationService, times(1))
                            .validateContactExistsByCellPhone(updateContactRequest.getCellPhone()),
                    () -> verify(contactRepository, never()).findById(anyLong()),
                    () -> verify(contactMapper, never()).convertToContact(any(), any()),
                    () -> verify(contactRepository, never()).save(any()),
                    () -> verify(contactMapper, never()).convertToContactResponse(any())
            );
        }

        @Test
        @DisplayName("Should throw exception if contact not found")
        void testUpdateContact_WhenContactNotFound_ShouldThrowException() {
            doThrow(new ContactApiException("Contact not found")).when(contactRepository)
                    .findById(updateContactRequest.getId());

            ContactApiException exception = assertThrows(ContactApiException.class,
                    () -> updateContactService.updateContact(updateContactRequest));

            assertAll(
                    () -> assertEquals("Contact not found", exception.getMessage()),

                    () -> verify(contactValidationService, times(1))
                            .validateContactExistsByCellPhone(updateContactRequest.getCellPhone()),
                    () -> verify(contactRepository, times(1))
                            .findById(updateContactRequest.getId()),
                    () -> verify(contactMapper, never()).convertToContact(any(), any()),
                    () -> verify(contactRepository, never()).save(any()),
                    () -> verify(contactMapper, never()).convertToContactResponse(any())
            );
        }
    }
}