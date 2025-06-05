package com.lgsoftworks.infrastructure.adapter.repository;

import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.infrastructure.adapter.entity.RequestPlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestPlayerRepository extends JpaRepository<RequestPlayerEntity, Long> {
    Optional<RequestPlayerEntity> findByPlayer_Id(Long playerId);
    List<RequestPlayerEntity> findByTeam_Id(Long teamId);
    boolean existsByPlayerIdAndStatusRequest(Long playerId, StatusRequest statusRequest);
}
