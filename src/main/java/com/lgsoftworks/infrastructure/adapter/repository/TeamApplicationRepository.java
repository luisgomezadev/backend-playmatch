package com.lgsoftworks.infrastructure.adapter.repository;

import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.infrastructure.adapter.entity.TeamApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamApplicationRepository extends JpaRepository<TeamApplicationEntity, Long> {
    List<TeamApplicationEntity> findByPlayer_Id(Long playerId);
    List<TeamApplicationEntity> findByTeam_Id(Long teamId);
    boolean existsByPlayerIdAndStatusRequest(Long playerId, StatusRequest statusRequest);
}
