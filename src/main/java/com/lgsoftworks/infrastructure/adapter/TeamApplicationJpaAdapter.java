package com.lgsoftworks.infrastructure.adapter;

import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.model.TeamApplication;
import com.lgsoftworks.domain.port.out.TeamApplicationRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.entity.RequestPlayerEntity;
import com.lgsoftworks.infrastructure.adapter.mapper.RequestPlayerDboMapper;
import com.lgsoftworks.infrastructure.adapter.repository.RequestPlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeamApplicationJpaAdapter implements TeamApplicationRepositoryPort {

    private final RequestPlayerRepository requestPlayerRepository;

    @Override
    public Optional<TeamApplication> findById(Long id) {
        Optional<RequestPlayerEntity> optionalRequestPlayer = requestPlayerRepository.findById(id);
        return optionalRequestPlayer.map(RequestPlayerDboMapper::toModel);
    }

    @Override
    public TeamApplication save(TeamApplication teamApplication) {
        RequestPlayerEntity saved = requestPlayerRepository.save(RequestPlayerDboMapper.toDbo(teamApplication));
        return RequestPlayerDboMapper.toModel(saved);
    }

    @Override
    public Optional<TeamApplication> findByPlayer(Long playerId) {
        Optional<RequestPlayerEntity> optionalRequestPlayer = requestPlayerRepository.findByPlayer_Id(playerId);
        return optionalRequestPlayer.map(RequestPlayerDboMapper::toModel);
    }

    @Override
    public List<TeamApplication> findByTeam(Long teamId) {
        List<RequestPlayerEntity> requestPlayerEntityList = requestPlayerRepository.findByTeam_Id(teamId);
        return requestPlayerEntityList.stream()
                .map(RequestPlayerDboMapper::toModel)
                .toList();
    }

    @Override
    public boolean existsByPlayerIdAndStatusRequest(Long playerId, StatusRequest statusRequest) {
        return requestPlayerRepository.existsByPlayerIdAndStatusRequest(playerId, statusRequest);
    }

}
