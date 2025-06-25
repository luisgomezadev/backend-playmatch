package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.TeamApplicationDTO;
import com.lgsoftworks.application.dto.request.TeamApplicationRequest;
import com.lgsoftworks.domain.enums.StatusRequest;

import java.util.List;
import java.util.Optional;

public interface TeamApplicationUseCase {
    Optional<TeamApplicationDTO> findById(Long id);
    TeamApplicationDTO save(TeamApplicationRequest requestPlayer);
    Optional<TeamApplicationDTO> findByPlayer(Long playerId);
    List<TeamApplicationDTO> findByTeam(Long teamId);
    boolean existsByPlayerIdAndStatusRequest(Long playerId, StatusRequest statusRequest);
}
