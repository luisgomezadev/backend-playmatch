package com.lgsoftworks.infrastructure.adapter.out.persistence.repository;

import com.lgsoftworks.domain.enums.Status;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<FieldEntity, Long>, JpaSpecificationExecutor<FieldEntity> {
    boolean existsByAdminId(Long id);
    Optional<FieldEntity> findByAdminId(Long id);
    List<FieldEntity> findAllByStatus(Status status);
}
