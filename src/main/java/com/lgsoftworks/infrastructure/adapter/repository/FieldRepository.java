package com.lgsoftworks.infrastructure.adapter.repository;

import com.lgsoftworks.infrastructure.adapter.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<FieldEntity, Long> {
    boolean existsByAdminId(Long id);
    Optional<FieldEntity> findByAdminId(Long id);
}
