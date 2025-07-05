package com.lgsoftworks.infrastructure.adapter.out.persistence;

import com.lgsoftworks.domain.model.Team;
import com.lgsoftworks.domain.port.out.TeamRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.TeamEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.TeamDboMapper;
import com.lgsoftworks.infrastructure.adapter.out.persistence.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeamJpaAdapter implements TeamRepositoryPort {

    private final TeamRepository teamRepository;

    @Override
    public List<Team> findAll() {
        List<TeamEntity> teamEntityList = teamRepository.findAll();
        return teamEntityList.stream()
                .map(TeamDboMapper::toModel)
                .toList();
    }

    @Override
    public Optional<Team> findById(Long id) {
        Optional<TeamEntity> optionalTeam = teamRepository.findById(id);
        return optionalTeam.map(TeamDboMapper::toModel);
    }

    @Override
    public Team save(Team team) {
        TeamEntity saved = teamRepository.save(TeamDboMapper.toDbo(team));
        return TeamDboMapper.toModel(saved);
    }

    @Override
    public boolean deleteById(Long id) {
        teamRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean existsByOwnerId(Long ownerId) {
        return teamRepository.existsByOwnerId(ownerId);
    }

    @Override
    public List<Team> findByCity(String city) {
        List<TeamEntity> teamEntityList = teamRepository.findByCity(city);
        return teamEntityList.stream()
                .map(TeamDboMapper::toModel)
                .toList();
    }

    @Override
    public Optional<Team> findByOwnerId(Long id) {
        Optional<TeamEntity> optionalTeam = teamRepository.findByOwnerId(id);
        return optionalTeam.map(TeamDboMapper::toModel);
    }
}
