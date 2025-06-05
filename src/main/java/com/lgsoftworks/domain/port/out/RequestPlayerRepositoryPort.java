package com.lgsoftworks.domain.port.out;

import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.model.RequestPlayer;

import java.util.List;
import java.util.Optional;

public interface RequestPlayerRepositoryPort {
    Optional<RequestPlayer> findById(Long id);
    RequestPlayer save(RequestPlayer requestPlayer);
    Optional<RequestPlayer> findByPlayer(Long playerId);
    List<RequestPlayer> findByTeam(Long teamId);
    boolean existsByPlayerIdAndStatusRequest(Long playerId, StatusRequest statusRequest);
}
