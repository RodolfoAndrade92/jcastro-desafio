package com.test.dev.contact.manager.application.service.impl;

import com.test.dev.contact.manager.application.exception.ContactApiException;
import com.test.dev.contact.manager.domain.repository.ContactRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Contact validation service tests")
class ContactValidationServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactValidationServiceImpl contactValidationService;

    @Test
    @DisplayName("Should throw exception when contact already exists")
    void testValidateContactExistsByCellPhone_WhenContactExists_ShouldThrowException() {
        String cellPhone = "1234567890";
        when(contactRepository.existsByCellPhone(cellPhone)).thenReturn(true);

        ContactApiException exception = assertThrows(ContactApiException.class,
                () -> contactValidationService.validateContactExistsByCellPhone(cellPhone));

        assertAll(
                () -> assertEquals("There is already a contact with the cell phone provided: " + cellPhone, exception.getMessage()),
                () -> verify(contactRepository, times(1)).existsByCellPhone(cellPhone)
        );
    }

    @Test
    @DisplayName("Should not throw exception when contact does not exist")
    void testValidateContactExistsByCellPhone_WhenContactDoesNotExist_ShouldNotThrowException() {
        String cellPhone = "1234567890";
        when(contactRepository.existsByCellPhone(cellPhone)).thenReturn(false);

        assertAll(
                () -> assertDoesNotThrow(() -> contactValidationService.validateContactExistsByCellPhone(cellPhone)),
                () -> verify(contactRepository, times(1)).existsByCellPhone(cellPhone)
        );
    }

}