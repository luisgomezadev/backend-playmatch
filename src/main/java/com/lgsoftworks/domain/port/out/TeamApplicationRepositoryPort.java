package com.lgsoftworks.domain.port.out;

import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.model.TeamApplication;

import java.util.List;
import java.util.Optional;

public interface TeamApplicationRepositoryPort {
    Optional<TeamApplication> findById(Long id);
    TeamApplication save(TeamApplication teamApplication);
    List<TeamApplication> findByPlayer(Long playerId);
    List<TeamApplication> findByTeam(Long teamId);
    boolean existsByPlayerIdAndStatusRequest(Long playerId, StatusRequest statusRequest);
}
