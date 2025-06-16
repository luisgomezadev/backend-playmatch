package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.infrastructure.rest.dto.PlayerDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.PlayerRequest;
import com.lgsoftworks.infrastructure.rest.dto.summary.PersonSummaryDTO;

import java.util.List;
import java.util.Optional;

public interface PlayerUseCase {
    List<PlayerDTO> findAll();
    Optional<PlayerDTO> findById(Long id);
    PersonSummaryDTO save(PlayerRequest playerRequest);
    List<PersonSummaryDTO> saveAll(List<PlayerRequest> playerRequests);
    PersonSummaryDTO update(PlayerRequest playerRequest);
    boolean deleteById(Long id);
    Optional<PlayerDTO> findByDocumentNumber(String documentNumber);
    Optional<PlayerDTO> findByEmail(String email);
    boolean existsByIdAndTeamId(Long playerId, Long teamId);
    List<PlayerDTO> findAllByTeamId(Long teamId);
}
