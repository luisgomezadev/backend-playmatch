package com.lgsoftworks.infrastructure.adapter;

import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.model.TeamApplication;
import com.lgsoftworks.domain.port.out.TeamApplicationRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.entity.TeamApplicationEntity;
import com.lgsoftworks.infrastructure.adapter.mapper.TeamApplicationDboMapper;
import com.lgsoftworks.infrastructure.adapter.repository.TeamApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeamApplicationJpaAdapter implements TeamApplicationRepositoryPort {

    private final TeamApplicationRepository teamApplicationRepository;

    @Override
    public Optional<TeamApplication> findById(Long id) {
        Optional<TeamApplicationEntity> optionalRequestPlayer = teamApplicationRepository.findById(id);
        return optionalRequestPlayer.map(TeamApplicationDboMapper::toModel);
    }

    @Override
    public TeamApplication save(TeamApplication teamApplication) {
        TeamApplicationEntity saved = teamApplicationRepository.save(TeamApplicationDboMapper.toDbo(teamApplication));
        return TeamApplicationDboMapper.toModel(saved);
    }

    @Override
    public List<TeamApplication> findByPlayer(Long playerId) {
        List<TeamApplicationEntity> playerApplicationEntityList = teamApplicationRepository.findByPlayer_Id(playerId);
        return playerApplicationEntityList.stream()
                .map(TeamApplicationDboMapper::toModel)
                .toList();
    }

    @Override
    public List<TeamApplication> findByTeam(Long teamId) {
        List<TeamApplicationEntity> teamApplicationEntityList = teamApplicationRepository.findByTeam_Id(teamId);
        return teamApplicationEntityList.stream()
                .map(TeamApplicationDboMapper::toModel)
                .toList();
    }

    @Override
    public boolean existsByPlayerIdAndStatusRequest(Long playerId, StatusRequest statusRequest) {
        return teamApplicationRepository.existsByPlayerIdAndStatusRequest(playerId, statusRequest);
    }

}
