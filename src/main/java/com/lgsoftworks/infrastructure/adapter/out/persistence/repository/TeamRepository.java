package com.lgsoftworks.infrastructure.adapter.out.persistence.repository;

import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    boolean existsByOwnerId(Long ownerId);
    List<TeamEntity> findByCity(String city);
    Optional<TeamEntity> findByOwnerId(Long id);
}
