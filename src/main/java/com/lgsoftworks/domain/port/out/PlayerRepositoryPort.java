package com.lgsoftworks.domain.port.out;

import com.lgsoftworks.domain.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepositoryPort {
    List<Player> findAll();
    Optional<Player> findById(Long id);
    Player save(Player player);
    List<Player> saveAll(List<Player> players);
    Player update(Player player);
    boolean deleteById(Long id);
    Optional<Player> findByDocumentNumber(String documentNumber);
    Optional<Player> findByEmail(String email);
    boolean existsByIdAndTeamId(Long playerId, Long teamId);
    List<Player> findAllByTeamId(Long teamId);
}
