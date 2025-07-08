package com.lgsoftworks.infrastructure.adapter.out.persistence;

import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.PlayerEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.PlayerDboMapper;
import com.lgsoftworks.infrastructure.adapter.out.persistence.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerJpaAdapter implements PlayerRepositoryPort {

    private final PlayerRepository playerRepository;

    @Override
    public Page<Player> findAllExcludingEmail(String excludedEmail, Pageable pageable) {
        return playerRepository
                .findByEmailNot(excludedEmail, pageable)
                .map(PlayerDboMapper::toModel);
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
    public boolean deleteById(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new UserByIdNotFoundException(id);
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
    public Optional<Player> findByCellphone(String cellphone) {
        return playerRepository.findByCellphone(cellphone)
                .map(PlayerDboMapper::toModel);
    }

    @Override
    public boolean existsByIdAndTeamId(Long playerId, Long teamId) {
        return playerRepository.existsByIdAndTeamId(playerId, teamId);
    }

    @Override
    public List<Player> findAllByTeamId(Long teamId) {
        List<PlayerEntity> playerEntityList = playerRepository.findAllByTeamId(teamId);
        return playerEntityList.stream()
                .map(PlayerDboMapper::toModel)
                .toList();
    }

}
