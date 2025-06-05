package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.RequestPlayerModelMapper;
import com.lgsoftworks.domain.dto.RequestPlayerDTO;
import com.lgsoftworks.domain.dto.request.RequestPlayerRequest;
import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.exception.PersonByIdNotFoundException;
import com.lgsoftworks.domain.exception.PlayerAlreadyHasPendingRequestException;
import com.lgsoftworks.domain.exception.TeamByIdNotFoundException;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.model.RequestPlayer;
import com.lgsoftworks.domain.model.Team;
import com.lgsoftworks.domain.port.in.RequestPlayerUseCase;
import com.lgsoftworks.domain.port.out.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RequestPlayerService implements RequestPlayerUseCase {

    private final RequestPlayerRepositoryPort requestPlayerRepositoryPort;
    private final TeamRepositoryPort teamRepositoryPort;
    private final PlayerRepositoryPort playerRepositoryPort;

    @Override
    public Optional<RequestPlayerDTO> findById(Long id) {
        Optional<RequestPlayer> optionalRequestPlayer = requestPlayerRepositoryPort.findById(id);
        return optionalRequestPlayer.map(RequestPlayerModelMapper::toDTO);
    }

    @Override
    public RequestPlayerDTO save(RequestPlayerRequest requestPlayerRequest) {

        Player player = playerRepositoryPort.findById(requestPlayerRequest.getPlayer().getId())
                .orElseThrow(() -> new PersonByIdNotFoundException(requestPlayerRequest.getPlayer().getId()));

        Team team = teamRepositoryPort.findById(requestPlayerRequest.getTeam().getId())
                .orElseThrow(() -> new TeamByIdNotFoundException(requestPlayerRequest.getTeam().getId()));

        boolean hasPending = requestPlayerRepositoryPort.existsByPlayerIdAndStatusRequest(requestPlayerRequest.getPlayer().getId(), StatusRequest.PENDING);
        if (hasPending) {
            throw new PlayerAlreadyHasPendingRequestException(player, team);
        }

        RequestPlayer requestPlayer = RequestPlayerModelMapper.toModelRequest(requestPlayerRequest);
        requestPlayer.setRequestDate(LocalDateTime.now());
        requestPlayer.setStatusRequest(StatusRequest.PENDING);
        requestPlayer.setPlayer(player);
        requestPlayer.setTeam(team);
        RequestPlayer savedRequestPlayer = requestPlayerRepositoryPort.save(requestPlayer);

        return RequestPlayerModelMapper.toDTO(savedRequestPlayer);
    }

    @Override
    public Optional<RequestPlayerDTO> findByPlayer(Long playerId) {
        Optional<RequestPlayer> optionalRequestPlayer = requestPlayerRepositoryPort.findByPlayer(playerId);
        return optionalRequestPlayer.map(RequestPlayerModelMapper::toDTO);
    }

    @Override
    public List<RequestPlayerDTO> findByTeam(Long teamId) {
        List<RequestPlayer> requestPlayerList = requestPlayerRepositoryPort.findByTeam(teamId);
        return requestPlayerList.stream()
                .map(RequestPlayerModelMapper::toDTO)
                .toList();
    }

    @Override
    public boolean existsByPlayerIdAndStatusRequest(Long playerId, StatusRequest statusRequest) {
        return requestPlayerRepositoryPort.existsByPlayerIdAndStatusRequest(playerId, statusRequest);
    }

}
