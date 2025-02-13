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
@DisplayName("Find active contact service tests")
public class FindActiveContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactMapper contactMapper;

    @InjectMocks
    private FindActiveContactServiceImpl findActiveContactService;

    @Nested
    @DisplayName("When to fetch for active contacts")
    class FindActiveContacts {

        private List<Contact> activeContacts;
        private List<ContactResponse> activeContactResponses;

        @BeforeEach
        void setUp() {
            activeContacts = Arrays.asList(
                    BuilderUtil.buildContact(),
                    BuilderUtil.buildContact()
            );

            activeContactResponses = Arrays.asList(
                    BuilderUtil.buildContactResponse(),
                    BuilderUtil.buildContactResponse()
            );
        }

        @Test
        @DisplayName("Should return a list of active contacts mapped to ContactResponse")
        void testFindActiveContacts_ShouldReturnListOfContactResponses() {
            when(contactRepository.findActives()).thenReturn(activeContacts);
            when(contactMapper.convertToContactResponseList(activeContacts)).thenReturn(activeContactResponses);

            List<ContactResponse> result = findActiveContactService.findActiveContacts();

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertEquals(2, result.size()),
                    () -> assertEquals(activeContactResponses, result),

                    () -> verify(contactRepository, times(1)).findActives(),
                    () -> verify(contactMapper, times(1))
                            .convertToContactResponseList(activeContacts)
            );
        }

        @Test
        @DisplayName("Should return an empty list when there are no active contacts")
        void testFindActiveContacts_WhenNoActiveContacts_ShouldReturnEmptyList() {
            when(contactRepository.findActives()).thenReturn(List.of());
            when(contactMapper.convertToContactResponseList(List.of())).thenReturn(List.of());

            List<ContactResponse> result = findActiveContactService.findActiveContacts();

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertTrue(result.isEmpty()),

                    () -> verify(contactRepository, times(1)).findActives(),
                    () -> verify(contactMapper, times(1))
                            .convertToContactResponseList(List.of())
            );
        }
    }
}