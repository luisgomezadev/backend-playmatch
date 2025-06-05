package com.lgsoftworks.infrastructure.adapter.repository;

import com.lgsoftworks.infrastructure.adapter.entity.PlayerEntity;
import com.lgsoftworks.infrastructure.adapter.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findByDocumentNumber(String documentNumber);
    Optional<PlayerEntity> findByEmail(String email);
}
