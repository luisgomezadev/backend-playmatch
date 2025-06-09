package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.infrastructure.rest.dto.TeamDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.TeamRequest;
import com.lgsoftworks.infrastructure.rest.dto.summary.TeamSummaryDTO;

import java.util.List;
import java.util.Optional;

public interface TeamUseCase {
    List<TeamDTO> findAll();
    Optional<TeamDTO> findById(Long id);
    TeamSummaryDTO save(TeamRequest teamRequest);
    TeamSummaryDTO update(TeamRequest teamRequest, Long id);
    boolean deleteById(Long id);
    boolean existsByOwnerId(Long ownerId);
    List<TeamDTO> findByCity(String city);
    Optional<TeamDTO> findByOwnerId(Long id);
}
