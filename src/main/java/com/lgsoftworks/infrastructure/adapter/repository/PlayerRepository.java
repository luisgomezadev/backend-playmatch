package com.lgsoftworks.infrastructure.adapter.repository;

import com.lgsoftworks.infrastructure.adapter.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findByDocumentNumber(String documentNumber);
    Optional<PlayerEntity> findByEmail(String email);
    List<PlayerEntity> findAllByTeamId(Long id);
    boolean existsByIdAndTeamId(Long playerId, Long teamId);
}
