package com.lgsoftworks.field.infrastructure.adapter.out.persistence.repository;

import com.lgsoftworks.field.infrastructure.adapter.out.persistence.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<FieldEntity, Long>, JpaSpecificationExecutor<FieldEntity> {
    List<FieldEntity> findByVenueId(Long venueId);
}
