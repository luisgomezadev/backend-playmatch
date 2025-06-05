package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.domain.dto.PlayerDTO;
import com.lgsoftworks.domain.dto.request.PlayerRequest;
import com.lgsoftworks.domain.dto.summary.PersonSummaryDTO;

import java.util.List;
import java.util.Optional;

public interface PlayerUseCase {
    List<PlayerDTO> findAll();
    Optional<PlayerDTO> findById(Long id);
    PersonSummaryDTO save(PlayerRequest playerRequest);
    List<PersonSummaryDTO> saveAll(List<PlayerRequest> playerRequests);
    PersonSummaryDTO update(PlayerRequest playerRequest, Long id);
    boolean deleteById(Long id);
    Optional<PlayerDTO> findByDocumentNumber(String documentNumber);
    Optional<PlayerDTO> findByEmail(String email);
    boolean existsByIdAndTeamId(Long playerId, Long teamId);
}
