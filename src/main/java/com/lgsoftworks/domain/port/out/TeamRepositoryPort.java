package com.lgsoftworks.domain.port.out;

import com.lgsoftworks.domain.dto.TeamDTO;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepositoryPort {
    List<Team> findAll();
    Optional<Team> findById(Long id);
    Team save(Team team);
    Team update(Team team, Long id);
    boolean deleteById(Long id);
    boolean existsByOwnerId(Long ownerId);
    List<Team> findByCity(String city);
    Optional<Team> findByOwnerId(Long id);
}
