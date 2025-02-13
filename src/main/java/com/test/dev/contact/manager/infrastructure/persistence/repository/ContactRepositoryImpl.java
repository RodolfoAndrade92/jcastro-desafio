package com.test.dev.contact.manager.infrastructure.persistence.repository;

import com.test.dev.contact.manager.domain.mapper.ContactMapper;
import com.test.dev.contact.manager.domain.model.Contact;
import com.test.dev.contact.manager.domain.repository.ContactRepository;
import com.test.dev.contact.manager.infrastructure.persistence.entity.ContactEntity;

import java.util.List;

public class ContactRepositoryImpl implements ContactRepository {

    final ContactMapper contactMapper;
    final JpaContactRepository jpaContactRepository;

    public ContactRepositoryImpl(ContactMapper contactMapper, JpaContactRepository jpaContactRepository) {
        this.contactMapper = contactMapper;
        this.jpaContactRepository = jpaContactRepository;
    }

    @Override
    public Contact save(Contact contact) {
        ContactEntity saved = jpaContactRepository.save(contactMapper.convertToContactEntity(contact));
        return contactMapper.convertToContact(saved);
    }

    @Override
    public List<Contact> findAll() {
        List<ContactEntity> contactEntityList = jpaContactRepository.findAll();
        return contactMapper.convertToContactList(contactEntityList);
    }

    @Override
    public Contact findById(Long id) {
        ContactEntity contactEntity = jpaContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        return contactMapper.convertToContact(contactEntity);
    }

    @Override
    public boolean existsByCellPhone(String cellPhone) {
        return jpaContactRepository.existsByCellPhone(cellPhone);
    }

    @Override
    public void delete(Long id) {
        jpaContactRepository.deleteById(id);
    }
}
