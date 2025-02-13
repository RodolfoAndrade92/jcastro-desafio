package com.test.dev.contact.manager.util;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.CreateContactRequest;
import com.test.dev.contact.manager.application.dto.UpdateContactRequest;
import com.test.dev.contact.manager.domain.model.Contact;
import com.test.dev.contact.manager.infrastructure.persistence.entity.ContactEntity;

import java.time.LocalDateTime;

public class BuilderUtil {

    public static Contact buildContact() {
        return Contact.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@email.com")
                .cellPhone("11912345678")
                .telephone("1112345678")
                .favorite(false)
                .active(true)
                .createDate(LocalDateTime.parse("2023-10-01T10:00:00"))
                .build();
    }

    public static ContactEntity buildContactEntity() {
        return ContactEntity.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@email.com")
                .cellPhone("11912345678")
                .telephone("1112345678")
                .favorite(false)
                .active(true)
                .createDate(LocalDateTime.parse("2023-10-01T10:00:00"))
                .build();
    }

    public static ContactResponse buildContactResponse() {
        return ContactResponse.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@email.com")
                .cellPhone("11912345678")
                .telephone("1112345678")
                .favorite(false)
                .active(true)
                .createDate(LocalDateTime.parse("2023-10-01T10:00:00"))
                .build();
    }

    public static CreateContactRequest buildCreateContactRequest() {
        return CreateContactRequest.builder()
                .name("John Doe")
                .email("john.doe@email.com")
                .cellPhone("11912345678")
                .telephone("1112345678")
                .favorite(false)
                .build();
    }

    public static UpdateContactRequest buildUpdateContactRequest() {
        return UpdateContactRequest.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@email.com")
                .cellPhone("11912345678")
                .telephone("1112345678")
                .favorite(false)
                .active(true)
                .build();
    }
}
