package com.lgsoftworks.infrastructure.adapter;

import com.lgsoftworks.domain.exception.PersonByIdNotFoundException;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.entity.PlayerEntity;
import com.lgsoftworks.infrastructure.adapter.mapper.PlayerDboMapper;
import com.lgsoftworks.infrastructure.adapter.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerJpaAdapter implements PlayerRepositoryPort {

    private final PlayerRepository playerRepository;

    @Override
    public List<Player> findAll() {
        List<PlayerEntity> playerEntityList = playerRepository.findAll();
        return playerEntityList.stream()
                .map(PlayerDboMapper::toModel)
                .toList();
    }

    @Override
    public Optional<Player> findById(Long id) {
        Optional<PlayerEntity> optionalPlayer = playerRepository.findById(id);
        return optionalPlayer.map(PlayerDboMapper::toModel);
    }

    @Override
    public Player save(Player player) {
        PlayerEntity savedPlayer = playerRepository.save(PlayerDboMapper.toDbo(player));
        return PlayerDboMapper.toModel(savedPlayer);
    }

    @Override
    public List<Player> saveAll(List<Player> players) {
        List<PlayerEntity> playerEntities = players.stream().map(PlayerDboMapper::toDbo).toList();
        List<PlayerEntity> savedListPlayers = playerRepository.saveAll(playerEntities);
        return savedListPlayers.stream()
                .map(PlayerDboMapper::toModel)
                .toList();
    }

    @Override
    public Player update(Player player, Long id) {
        if (!playerRepository.existsById(id)) {
            throw new PersonByIdNotFoundException(id);
        }
        player.setId(id);
        PlayerEntity updatedPlayer = playerRepository.save(PlayerDboMapper.toDbo(player));
        return PlayerDboMapper.toModel(updatedPlayer);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new PersonByIdNotFoundException(id);
        }
        playerRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Player> findByDocumentNumber(String documentNumber) {
        return playerRepository.findByDocumentNumber(documentNumber)
                .map(PlayerDboMapper::toModel);
    }

    @Override
    public Optional<Player> findByEmail(String email) {
        return playerRepository.findByEmail(email)
                .map(PlayerDboMapper::toModel);
    }

    @Override
    public boolean existsByIdAndTeamId(Long playerId, Long teamId) {
        return playerRepository.existsByIdAndTeamId(playerId, teamId);
    }

}
