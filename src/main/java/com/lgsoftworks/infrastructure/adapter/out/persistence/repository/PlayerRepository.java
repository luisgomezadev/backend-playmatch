package com.lgsoftworks.infrastructure.adapter.out.persistence.repository;

import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findByDocumentNumber(String documentNumber);
    Optional<PlayerEntity> findByEmail(String email);
    Optional<PlayerEntity> findByCellphone(String cellphone);
    List<PlayerEntity> findAllByTeamId(Long id);
    boolean existsByIdAndTeamId(Long playerId, Long teamId);
}
