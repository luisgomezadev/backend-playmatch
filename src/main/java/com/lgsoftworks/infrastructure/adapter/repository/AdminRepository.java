package com.lgsoftworks.infrastructure.adapter.repository;

import com.lgsoftworks.infrastructure.adapter.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByDocumentNumber(String documentNumber);
    Optional<AdminEntity> findByEmail(String email);
}
