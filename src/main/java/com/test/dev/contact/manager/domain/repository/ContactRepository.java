package com.test.dev.contact.manager.domain.repository;

import com.test.dev.contact.manager.domain.model.Contact;

import java.util.List;

public interface ContactRepository {

    Contact save(Contact contact);

    List<Contact> findAll();

    List<Contact> findActives();

    Contact findById(Long id);

    boolean existsByCellPhone(String cellPhone);

    void delete(Long id);
}
