package com.lgsoftworks.infrastructure.adapter.out.persistence.repository;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<FieldEntity, Long>, JpaSpecificationExecutor<FieldEntity> {
}
