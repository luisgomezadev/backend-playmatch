package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.TeamApplicationModelMapper;
import com.lgsoftworks.application.dto.TeamApplicationDTO;
import com.lgsoftworks.application.dto.request.TeamApplicationRequest;
import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.exception.PlayerAlreadyHasPendingRequestException;
import com.lgsoftworks.domain.exception.TeamByIdNotFoundException;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.model.TeamApplication;
import com.lgsoftworks.domain.model.Team;
import com.lgsoftworks.domain.port.in.TeamApplicationUseCase;
import com.lgsoftworks.domain.port.out.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TeamApplicationService implements TeamApplicationUseCase {

    private final TeamApplicationRepositoryPort teamApplicationRepositoryPort;
    private final TeamRepositoryPort teamRepositoryPort;
    private final PlayerRepositoryPort playerRepositoryPort;

    @Override
    public Optional<TeamApplicationDTO> findById(Long id) {
        Optional<TeamApplication> optionalRequestPlayer = teamApplicationRepositoryPort.findById(id);
        return optionalRequestPlayer.map(TeamApplicationModelMapper::toDTO);
    }

    @Override
    public TeamApplicationDTO save(TeamApplicationRequest teamApplicationRequest) {

        Player player = playerRepositoryPort.findById(teamApplicationRequest.getPlayer().getId())
                .orElseThrow(() -> new UserByIdNotFoundException(teamApplicationRequest.getPlayer().getId()));

        Team team = teamRepositoryPort.findById(teamApplicationRequest.getTeam().getId())
                .orElseThrow(() -> new TeamByIdNotFoundException(teamApplicationRequest.getTeam().getId()));

        boolean hasPending = teamApplicationRepositoryPort.existsByPlayerIdAndStatusRequest(teamApplicationRequest.getPlayer().getId(), StatusRequest.PENDING);
        if (hasPending) {
            throw new PlayerAlreadyHasPendingRequestException(player, team);
        }

        TeamApplication teamApplication = TeamApplicationModelMapper.toModelRequest(teamApplicationRequest);
        teamApplication.setApplicationDate(LocalDateTime.now());
        teamApplication.setStatusRequest(StatusRequest.PENDING);
        teamApplication.setPlayer(player);
        teamApplication.setTeam(team);
        TeamApplication savedTeamApplication = teamApplicationRepositoryPort.save(teamApplication);

        return TeamApplicationModelMapper.toDTO(savedTeamApplication);
    }

    @Override
    public Optional<TeamApplicationDTO> findByPlayer(Long playerId) {
        Optional<TeamApplication> optionalRequestPlayer = teamApplicationRepositoryPort.findByPlayer(playerId);
        return optionalRequestPlayer.map(TeamApplicationModelMapper::toDTO);
    }

    @Override
    public List<TeamApplicationDTO> findByTeam(Long teamId) {
        List<TeamApplication> teamApplicationList = teamApplicationRepositoryPort.findByTeam(teamId);
        return teamApplicationList.stream()
                .map(TeamApplicationModelMapper::toDTO)
                .toList();
    }

    @Override
    public boolean existsByPlayerIdAndStatusRequest(Long playerId, StatusRequest statusRequest) {
        return teamApplicationRepositoryPort.existsByPlayerIdAndStatusRequest(playerId, statusRequest);
    }

}
