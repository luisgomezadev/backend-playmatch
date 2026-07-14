package com.lgsoftworks.venue.infrastructure.adapter.out.persistence.repository;

import com.lgsoftworks.venue.infrastructure.adapter.out.persistence.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VenueRepository extends JpaRepository<VenueEntity, Long>, JpaSpecificationExecutor<VenueEntity> {
    Optional<VenueEntity> findByCode(String code);
    Optional<VenueEntity> findByAdminId(Long adminId);
}
