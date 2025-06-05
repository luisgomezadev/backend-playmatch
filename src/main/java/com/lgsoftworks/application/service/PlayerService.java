package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.PersonModelMapper;
import com.lgsoftworks.application.mapper.PlayerModelMapper;
import com.lgsoftworks.domain.dto.PlayerDTO;
import com.lgsoftworks.domain.dto.request.PlayerRequest;
import com.lgsoftworks.domain.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.domain.exception.PersonByDocumentNotFoundException;
import com.lgsoftworks.domain.exception.PersonByEmailNotFoundException;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
import com.lgsoftworks.domain.port.out.AdminRepositoryPort;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.domain.validator.ValidatePerson;

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
    public List<PlayerDTO> findAll() {
        List<Player> playerList = playerRepositoryPort.findAll();
        return playerList.stream()
                .map(PlayerModelMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<PlayerDTO> findById(Long id) {
        Optional<Player> optionalPlayer = playerRepositoryPort.findById(id);
        return optionalPlayer.map(PlayerModelMapper::toDTO);
    }

    @Override
    public PersonSummaryDTO save(PlayerRequest playerRequest) {
        validatePerson.validate(playerRequest.getDocumentNumber(), playerRequest.getEmail());
        Player savedPerson = playerRepositoryPort.save(PlayerModelMapper.toModelRequest(playerRequest));
        return PersonModelMapper.toPersonSummary(savedPerson);
    }

    @Override
    public List<PersonSummaryDTO> saveAll(List<PlayerRequest> playerRequests) {
        for (PlayerRequest p : playerRequests) {
            validatePerson.validate(p.getDocumentNumber(), p.getEmail());
        }

        List<Player> players = playerRequests.stream()
                .map(PlayerModelMapper::toModelRequest)
                .toList();

        List<Player> savedPlayers = playerRepositoryPort.saveAll(players);

        return savedPlayers.stream()
                .map(PersonModelMapper::toPersonSummary)
                .toList();
    }

    @Override
    public PersonSummaryDTO update(PlayerRequest playerRequest, Long id) {
        Player updatedPlayer = playerRepositoryPort.update(PlayerModelMapper.toModelRequest(playerRequest), id);
        return PersonModelMapper.toPersonSummary(updatedPlayer);
    }

    @Override
    public boolean deleteById(Long id) {
        return playerRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<PlayerDTO> findByDocumentNumber(String documentNumber) {
        Optional<Player> optionalPlayer = playerRepositoryPort.findByDocumentNumber(documentNumber);
        if (optionalPlayer.isEmpty()) {
            throw new PersonByDocumentNotFoundException(documentNumber);
        }
        return optionalPlayer.map(PlayerModelMapper::toDTO);
    }

    @Override
    public Optional<PlayerDTO> findByEmail(String email) {
        Optional<Player> optionalPlayer = playerRepositoryPort.findByEmail(email);
        if (optionalPlayer.isEmpty()) {
            throw new PersonByEmailNotFoundException(email);
        }
        return optionalPlayer.map(PlayerModelMapper::toDTO);
    }

}
