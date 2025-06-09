package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.infrastructure.rest.dto.RequestPlayerDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.RequestPlayerRequest;
import com.lgsoftworks.domain.enums.StatusRequest;

import java.util.List;
import java.util.Optional;

public interface RequestPlayerUseCase {
    Optional<RequestPlayerDTO> findById(Long id);
    RequestPlayerDTO save(RequestPlayerRequest requestPlayer);
    Optional<RequestPlayerDTO> findByPlayer(Long playerId);
    List<RequestPlayerDTO> findByTeam(Long teamId);
    boolean existsByPlayerIdAndStatusRequest(Long playerId, StatusRequest statusRequest);
}
