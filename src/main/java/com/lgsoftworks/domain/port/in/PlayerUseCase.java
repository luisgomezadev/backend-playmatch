package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.PlayerDTO;
import com.lgsoftworks.application.dto.request.PlayerRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.application.dto.summary.PlayerSummaryDTO;

import java.util.List;
import java.util.Optional;

public interface PlayerUseCase {
    List<PlayerSummaryDTO> findAll();
    Optional<PlayerSummaryDTO> findById(Long id);
    UserDTO save(PlayerRequest playerRequest);
    List<UserDTO> saveAll(List<PlayerRequest> playerRequests);
    UserDTO update(PlayerRequest playerRequest);
    boolean deleteById(Long id);
    Optional<PlayerDTO> findByDocumentNumber(String documentNumber);
    Optional<PlayerDTO> findByEmail(String email);
    boolean existsByIdAndTeamId(Long playerId, Long teamId);
    List<PlayerSummaryDTO> findAllByTeamId(Long teamId);
}
