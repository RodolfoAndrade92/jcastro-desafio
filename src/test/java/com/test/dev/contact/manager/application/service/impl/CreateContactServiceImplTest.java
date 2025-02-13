package com.test.dev.contact.manager.application.service.impl;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.CreateContactRequest;
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
@DisplayName("Create contact service tests")
public class CreateContactServiceImplTest {

    @Mock
    private ContactValidationService contactValidationService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactMapper contactMapper;

    @InjectMocks
    private CreateContactServiceImpl createContactService;

    private CreateContactRequest createContactRequest;
    private Contact contact;
    private ContactResponse contactResponse;

    @BeforeEach
    public void setUp() {
        createContactRequest = BuilderUtil.buildCreateContactRequest();
        contact = BuilderUtil.buildContact();
        contactResponse = BuilderUtil.buildContactResponse();
    }

    @Nested
    @DisplayName("When to create a new contact")
    class CreateContact {

        @Test
        @DisplayName("Should validate the cell phone, save the contact and return the correct answer")
        void testCreateContact_ShouldValidateSaveAndReturnResponse() {
            when(contactMapper.convertToContact(createContactRequest)).thenReturn(contact);
            when(contactRepository.save(contact)).thenReturn(contact);
            when(contactMapper.convertToContactResponse(contact)).thenReturn(contactResponse);

            ContactResponse result = createContactService.createContact(createContactRequest);

            assertAll(
                    () -> verify(contactValidationService, times(1)).validateContactExistsByCellPhone(createContactRequest.getCellPhone()),
                    () -> verify(contactMapper, times(1)).convertToContact(createContactRequest),
                    () -> verify(contactRepository, times(1)).save(contact),
                    () -> verify(contactMapper, times(1)).convertToContactResponse(contact),

                    () -> assertNotNull(result),
                    () -> assertEquals(contactResponse.getId(), result.getId()),
                    () -> assertEquals(contactResponse.getName(), result.getName()),
                    () -> assertEquals(contactResponse.getCellPhone(), result.getCellPhone())
            );
        }
    }

    @Nested
    @DisplayName("When an error occurs in cell phone validation")
    class ValidationError {

        @Test
        @DisplayName("Should throw exception and not save contact")
        void testCreateContact_WhenValidationFails_ShouldThrowException() {
            String cellPhone = createContactRequest.getCellPhone();
            doThrow(new ContactApiException("There is already a contact with the cell phone provided: " + cellPhone))
                    .when(contactValidationService).validateContactExistsByCellPhone(cellPhone);

            ContactApiException exception = assertThrows(ContactApiException.class, () -> {
                createContactService.createContact(createContactRequest);
            });

            assertAll(
                    () -> assertEquals("There is already a contact with the cell phone provided: " + cellPhone, exception.getMessage()),
                    () -> verify(contactValidationService, times(1)).validateContactExistsByCellPhone(cellPhone),
                    () -> verify(contactMapper, never()).convertToContact((CreateContactRequest) any()),
                    () -> verify(contactRepository, never()).save(any()),
                    () -> verify(contactMapper, never()).convertToContactResponse(any())
            );
        }
    }
}