package com.lgsoftworks.infrastructure.adapter.out.persistence.repository;

import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldAdminRepository extends JpaRepository<FieldAdminEntity, Long> {
    Optional<FieldAdminEntity> findByDocumentNumber(String documentNumber);
    Optional<FieldAdminEntity> findByEmail(String email);
    Optional<FieldAdminEntity> findByCellphone(String cellphone);
}
