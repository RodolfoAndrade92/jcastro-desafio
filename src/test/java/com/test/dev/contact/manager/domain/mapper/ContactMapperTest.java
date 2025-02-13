package com.test.dev.contact.manager.domain.mapper;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.CreateContactRequest;
import com.test.dev.contact.manager.application.dto.UpdateContactRequest;
import com.test.dev.contact.manager.domain.model.Contact;
import com.test.dev.contact.manager.infrastructure.persistence.entity.ContactEntity;
import com.test.dev.contact.manager.util.BuilderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Contact mapper tests")
public class ContactMapperTest {

    @InjectMocks
    private ContactMapper contactMapper;

    @Nested
    @DisplayName("When to convert CreateContactRequest to Contact")
    class ConvertCreateContactRequestToContact {

        private CreateContactRequest createContactRequest;

        @BeforeEach
        void setUp() {
            createContactRequest = BuilderUtil.buildCreateContactRequest();
        }

        @Test
        @DisplayName("Should return a Contact with the correct data")
        void testConvertToContact_FromCreateContactRequest() {
            Contact contact = contactMapper.convertToContact(createContactRequest);

            assertAll(
                    () -> assertNotNull(contact),
                    () -> assertEquals("John Doe", contact.getName()),
                    () -> assertEquals("john.doe@email.com", contact.getEmail()),
                    () -> assertEquals("11912345678", contact.getCellPhone()),
                    () -> assertEquals("1112345678", contact.getTelephone()),
                    () -> assertFalse(contact.getFavorite()),
                    () -> assertTrue(contact.getActive()),
                    () -> assertNotNull(contact.getCreateDate())
            );
        }
    }

    @Nested
    @DisplayName("When to convert UpdateContactRequest to Contact")
    class ConvertUpdateContactRequestToContact {

        private UpdateContactRequest updateContactRequest;
        private Contact contact;

        @BeforeEach
        void setUp() {
            updateContactRequest = BuilderUtil.buildUpdateContactRequest();
            contact = BuilderUtil.buildContact();
        }

        @Test
        @DisplayName("Should update only non-null fields of Contact")
        void testConvertToContact_FromUpdateContactRequest() {
            Contact updatedContact = contactMapper.convertToContact(updateContactRequest, contact);

            assertAll(
                    () -> assertNotNull(updatedContact),
                    () -> assertEquals("John Doe", updatedContact.getName()),
                    () -> assertEquals("john.doe@email.com", updatedContact.getEmail()),
                    () -> assertEquals("11912345678", updatedContact.getCellPhone()),
                    () -> assertEquals("1112345678", updatedContact.getTelephone()),
                    () -> assertFalse(updatedContact.getFavorite()),
                    () -> assertTrue(updatedContact.getActive()),
                    () -> assertNotNull(updatedContact.getCreateDate())
            );
        }
    }

    @Nested
    @DisplayName("When to convert ContactEntity to Contact")
    class ConvertContactEntityToContact {

        private ContactEntity contactEntity;

        @BeforeEach
        void setUp() {
            contactEntity = BuilderUtil.buildContactEntity();
        }

        @Test
        @DisplayName("Should return a Contact with the correct data")
        void testConvertToContact_FromContactEntity() {
            Contact contact = contactMapper.convertToContact(contactEntity);

            assertAll(
                    () -> assertNotNull(contact),
                    () -> assertEquals(1L, contact.getId()),
                    () -> assertEquals("John Doe", contact.getName()),
                    () -> assertEquals("john.doe@email.com", contact.getEmail()),
                    () -> assertEquals("11912345678", contact.getCellPhone()),
                    () -> assertEquals("1112345678", contact.getTelephone()),
                    () -> assertFalse(contact.getFavorite()),
                    () -> assertTrue(contact.getActive()),
                    () -> assertNotNull(contact.getCreateDate())
            );
        }
    }

    @Nested
    @DisplayName("When to convert Contact to ContactEntity")
    class ConvertContactToContactEntity {

        private Contact contact;

        @BeforeEach
        void setUp() {
            contact = BuilderUtil.buildContact();
        }

        @Test
        @DisplayName("Should return a ContactEntity with the correct data")
        void testConvertToContactEntity() {
            ContactEntity contactEntity = contactMapper.convertToContactEntity(contact);

            assertAll(
                    () -> assertNotNull(contactEntity),
                    () -> assertEquals(1L, contactEntity.getId()),
                    () -> assertEquals("John Doe", contactEntity.getName()),
                    () -> assertEquals("john.doe@email.com", contactEntity.getEmail()),
                    () -> assertEquals("11912345678", contactEntity.getCellPhone()),
                    () -> assertEquals("1112345678", contactEntity.getTelephone()),
                    () -> assertFalse(contactEntity.getFavorite()),
                    () -> assertTrue(contactEntity.getActive()),
                    () -> assertNotNull(contactEntity.getCreateDate())
            );
        }
    }

    @Nested
    @DisplayName("When to convert a list from ContactEntity to Contact")
    class ConvertContactEntityListToContactList {

        private List<ContactEntity> contactEntityList;

        @BeforeEach
        void setUp() {
            contactEntityList = new ArrayList<>(List.of(BuilderUtil.buildContactEntity()));
        }

        @Test
        @DisplayName("Should return a list of Contacts with the correct data.")
        void testConvertToContactList() {
            List<Contact> contactList = contactMapper.convertToContactList(contactEntityList);
            Contact contact = contactList.get(0);

            assertAll(
                    () -> assertNotNull(contactList),
                    () -> assertEquals(1, contactList.size()),
                    () -> assertEquals(1L, contact.getId()),
                    () -> assertEquals("John Doe", contact.getName()),
                    () -> assertEquals("john.doe@email.com", contact.getEmail()),
                    () -> assertEquals("11912345678", contact.getCellPhone()),
                    () -> assertEquals("1112345678", contact.getTelephone()),
                    () -> assertFalse(contact.getFavorite()),
                    () -> assertTrue(contact.getActive()),
                    () -> assertNotNull(contact.getCreateDate())
            );
        }
    }

    @Nested
    @DisplayName("When to convert Contact to ContactResponse")
    class ConvertContactToContactResponse {

        private Contact contact;

        @BeforeEach
        void setUp() {
            contact = BuilderUtil.buildContact();
        }

        @Test
        @DisplayName("Should return a ContactResponse with the correct data.")
        void testConvertToContactResponse() {
            ContactResponse contactResponse = contactMapper.convertToContactResponse(contact);

            assertAll(
                    () -> assertNotNull(contactResponse),
                    () -> assertEquals(1L, contactResponse.getId()),
                    () -> assertEquals("John Doe", contactResponse.getName()),
                    () -> assertEquals("john.doe@email.com", contactResponse.getEmail()),
                    () -> assertEquals("11912345678", contactResponse.getCellPhone()),
                    () -> assertEquals("1112345678", contactResponse.getTelephone()),
                    () -> assertFalse(contactResponse.getFavorite()),
                    () -> assertTrue(contactResponse.getActive()),
                    () -> assertNotNull(contactResponse.getCreateDate())
            );
        }
    }

    @Nested
    @DisplayName("When to convert a Contact list to ContactResponse")
    class ConvertContactListToContactResponseList {

        private List<Contact> contactList;

        @BeforeEach
        void setUp() {
            contactList = new ArrayList<>(List.of(BuilderUtil.buildContact()));
        }

        @Test
        @DisplayName("Should return a list of ContactResponse with the correct data")
        void testConvertToContactResponseList() {
            List<ContactResponse> contactResponseList = contactMapper.convertToContactResponseList(contactList);
            ContactResponse contactResponse = contactResponseList.get(0);

            assertAll(
                    () -> assertNotNull(contactResponseList),
                    () -> assertEquals(1, contactResponseList.size()),
                    () -> assertEquals(1L, contactResponse.getId()),
                    () -> assertEquals("John Doe", contactResponse.getName()),
                    () -> assertEquals("john.doe@email.com", contactResponse.getEmail()),
                    () -> assertEquals("11912345678", contactResponse.getCellPhone()),
                    () -> assertEquals("1112345678", contactResponse.getTelephone()),
                    () -> assertFalse(contactResponse.getFavorite()),
                    () -> assertTrue(contactResponse.getActive()),
                    () -> assertNotNull(contactResponse.getCreateDate())
            );
        }
    }
}