package com.test.dev.contact.manager.application.service.impl;

import com.test.dev.contact.manager.application.dto.ContactResponse;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Find all contact service tests")
public class FindAllContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactMapper contactMapper;

    @InjectMocks
    private FindAllContactServiceImpl findAllContactService;

    @Nested
    @DisplayName("When to fetch all contacts")
    class FindAllContacts {

        private List<Contact> contactList;
        private List<ContactResponse> contactResponseList;

        @BeforeEach
        void setUp() {
            contactList = Arrays.asList(
                    BuilderUtil.buildContact(),
                    BuilderUtil.buildContact()
            );

            contactResponseList = Arrays.asList(
                    BuilderUtil.buildContactResponse(),
                    BuilderUtil.buildContactResponse()
            );
        }

        @Test
        @DisplayName("Should return a list of ContactResponse when there are registered contacts")
        void testFindAllContacts_WhenContactsExist_ShouldReturnContactResponseList() {
            when(contactRepository.findAll()).thenReturn(contactList);
            when(contactMapper.convertToContactResponseList(contactList)).thenReturn(contactResponseList);

            List<ContactResponse> result = findAllContactService.findAllContacts();

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertEquals(2, result.size()),
                    () -> assertEquals(contactResponseList, result),

                    () -> verify(contactRepository, times(1)).findAll(),
                    () -> verify(contactMapper, times(1))
                            .convertToContactResponseList(contactList)
            );
        }

        @Test
        @DisplayName("Should return an empty list when there are no registered contacts")
        void testFindAllContacts_WhenNoContactsExist_ShouldReturnEmptyList() {
            when(contactRepository.findAll()).thenReturn(List.of());
            when(contactMapper.convertToContactResponseList(List.of())).thenReturn(List.of());

            List<ContactResponse> result = findAllContactService.findAllContacts();

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertTrue(result.isEmpty()),

                    () -> verify(contactRepository, times(1)).findAll(),
                    () -> verify(contactMapper, times(1)).convertToContactResponseList(List.of())
            );
        }
    }
}