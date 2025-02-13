package com.test.dev.contact.manager.infrastructure.persistence.repository;

import com.test.dev.contact.manager.infrastructure.persistence.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaContactRepository extends JpaRepository<ContactEntity, Long> {

    boolean existsByCellPhone(String cellPhone);

    List<ContactEntity> findByActiveTrue();
}
