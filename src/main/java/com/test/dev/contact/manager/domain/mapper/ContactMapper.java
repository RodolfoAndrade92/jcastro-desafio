package com.test.dev.contact.manager.domain.mapper;

import com.test.dev.contact.manager.application.dto.ContactResponse;
import com.test.dev.contact.manager.application.dto.CreateContactRequest;
import com.test.dev.contact.manager.application.dto.UpdateContactRequest;
import com.test.dev.contact.manager.domain.model.Contact;
import com.test.dev.contact.manager.infrastructure.persistence.entity.ContactEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ContactMapper {

    public Contact convertToContact(CreateContactRequest createContactRequest) {
        return Contact.builder()
                .name(createContactRequest.getName())
                .email(createContactRequest.getEmail())
                .cellPhone(createContactRequest.getCellPhone())
                .telephone(createContactRequest.getTelephone())
                .favorite(createContactRequest.getFavorite())
                .active(true)
                .createDate(LocalDateTime.now())
                .build();
    }

    public Contact convertToContact(UpdateContactRequest updateContactRequest, Contact contact) {
        Optional.ofNullable(updateContactRequest.getName()).ifPresent(contact::setName);
        Optional.ofNullable(updateContactRequest.getEmail()).ifPresent(contact::setEmail);
        Optional.ofNullable(updateContactRequest.getCellPhone()).ifPresent(contact::setCellPhone);
        Optional.ofNullable(updateContactRequest.getTelephone()).ifPresent(contact::setTelephone);
        Optional.ofNullable(updateContactRequest.getFavorite()).ifPresent(contact::setFavorite);
        Optional.ofNullable(updateContactRequest.getActive()).ifPresent(contact::setActive);
        return contact;
    }

    public Contact convertToContact(ContactEntity contactEntity) {
        return Contact.builder()
                .id(contactEntity.getId())
                .name(contactEntity.getName())
                .email(contactEntity.getEmail())
                .cellPhone(contactEntity.getCellPhone())
                .telephone(contactEntity.getTelephone())
                .favorite(contactEntity.getFavorite())
                .active(contactEntity.getActive())
                .createDate(contactEntity.getCreateDate())
                .build();
    }

    public ContactEntity convertToContactEntity(Contact contact) {
        return ContactEntity.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.getEmail())
                .cellPhone(contact.getCellPhone())
                .telephone(contact.getTelephone())
                .favorite(contact.getFavorite())
                .active(contact.getActive())
                .createDate(contact.getCreateDate())
                .build();
    }

    public List<Contact> convertToContactList(List<ContactEntity> contactEntityList) {
        List<Contact> contactList = new ArrayList<>();
        contactEntityList.forEach(contactEntity -> contactList.add(this.convertToContact(contactEntity)));
        return contactList;
    }

    public ContactResponse convertToContactResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.getEmail())
                .cellPhone(contact.getCellPhone())
                .telephone(contact.getTelephone())
                .favorite(contact.getFavorite())
                .active(contact.getActive())
                .createDate(contact.getCreateDate())
                .build();
    }

    public List<ContactResponse> convertToContactResponseList(List<Contact> contactList) {
        List<ContactResponse> contactResponseList = new ArrayList<>();
        contactList.forEach(contact -> contactResponseList.add(this.convertToContactResponse(contact)));
        return contactResponseList;
    }
}
