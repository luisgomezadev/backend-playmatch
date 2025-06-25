package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.UserModelMapper;
import com.lgsoftworks.application.mapper.PlayerModelMapper;
import com.lgsoftworks.application.dto.PlayerDTO;
import com.lgsoftworks.application.dto.request.PlayerRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.domain.exception.UserByDocumentNotFoundException;
import com.lgsoftworks.domain.exception.UserByEmailNotFoundException;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
import com.lgsoftworks.domain.port.out.AdminRepositoryPort;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.domain.validation.ValidatePerson;
import com.lgsoftworks.application.dto.summary.PlayerSummaryDTO;

import java.util.List;
import java.util.Optional;

public class PlayerService implements PlayerUseCase {

    private final PlayerRepositoryPort playerRepositoryPort;
    private final ValidatePerson validatePerson;

    public PlayerService(PlayerRepositoryPort playerRepositoryPort, AdminRepositoryPort adminRepositoryPort) {
        this.playerRepositoryPort = playerRepositoryPort;
        this.validatePerson = new ValidatePerson(adminRepositoryPort, playerRepositoryPort);
    }

    @Override
    public List<PlayerSummaryDTO> findAll() {
        List<Player> playerList = playerRepositoryPort.findAll();
        return playerList.stream()
                .map(PlayerModelMapper::toSummaryDTO)
                .toList();
    }

    @Override
    public Optional<PlayerSummaryDTO> findById(Long id) {
        Optional<Player> optionalPlayer = playerRepositoryPort.findById(id);
        return optionalPlayer.map(PlayerModelMapper::toSummaryDTO);
    }

    @Override
    public UserDTO save(PlayerRequest playerRequest) {
        validatePerson.validate(playerRequest.getDocumentNumber(), playerRequest.getEmail());
        Player savedPerson = playerRepositoryPort.save(PlayerModelMapper.toModelRequest(playerRequest));
        return UserModelMapper.toPersonSummary(savedPerson);
    }

    @Override
    public List<UserDTO> saveAll(List<PlayerRequest> playerRequests) {
        for (PlayerRequest p : playerRequests) {
            validatePerson.validate(p.getDocumentNumber(), p.getEmail());
        }

        List<Player> players = playerRequests.stream()
                .map(PlayerModelMapper::toModelRequest)
                .toList();

        List<Player> savedPlayers = playerRepositoryPort.saveAll(players);

        return savedPlayers.stream()
                .map(UserModelMapper::toPersonSummary)
                .toList();
    }

    @Override
    public UserDTO update(PlayerRequest playerRequest) {
        Player updatedPlayer = playerRepositoryPort.save(PlayerModelMapper.toModelRequest(playerRequest));
        return UserModelMapper.toPersonSummary(updatedPlayer);
    }

    @Override
    public boolean deleteById(Long id) {
        return playerRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<PlayerDTO> findByDocumentNumber(String documentNumber) {
        Optional<Player> optionalPlayer = playerRepositoryPort.findByDocumentNumber(documentNumber);
        if (optionalPlayer.isEmpty()) {
            throw new UserByDocumentNotFoundException(documentNumber);
        }
        return optionalPlayer.map(PlayerModelMapper::toDTO);
    }

    @Override
    public Optional<PlayerDTO> findByEmail(String email) {
        Optional<Player> optionalPlayer = playerRepositoryPort.findByEmail(email);
        if (optionalPlayer.isEmpty()) {
            throw new UserByEmailNotFoundException(email);
        }
        return optionalPlayer.map(PlayerModelMapper::toDTO);
    }

    @Override
    public boolean existsByIdAndTeamId(Long playerId, Long teamId) {
        return playerRepositoryPort.existsByIdAndTeamId(playerId, teamId);
    }

    @Override
    public List<PlayerSummaryDTO> findAllByTeamId(Long teamId) {
        List<Player> playerList = playerRepositoryPort.findAllByTeamId(teamId);
        return playerList.stream()
                .map(PlayerModelMapper::toSummaryDTO)
                .toList();
    }

}
