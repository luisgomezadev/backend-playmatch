package com.lgsoftworks.infrastructure.adapter;

import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.model.RequestPlayer;
import com.lgsoftworks.domain.port.out.RequestPlayerRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.entity.RequestPlayerEntity;
import com.lgsoftworks.infrastructure.adapter.mapper.RequestPlayerDboMapper;
import com.lgsoftworks.infrastructure.adapter.repository.RequestPlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequestPlayerJpaAdapter implements RequestPlayerRepositoryPort {

    private final RequestPlayerRepository requestPlayerRepository;

    @Override
    public Optional<RequestPlayer> findById(Long id) {
        Optional<RequestPlayerEntity> optionalRequestPlayer = requestPlayerRepository.findById(id);
        return optionalRequestPlayer.map(RequestPlayerDboMapper::toModel);
    }

    @Override
    public RequestPlayer save(RequestPlayer requestPlayer) {
        RequestPlayerEntity saved = requestPlayerRepository.save(RequestPlayerDboMapper.toDbo(requestPlayer));
        return RequestPlayerDboMapper.toModel(saved);
    }

    @Override
    public Optional<RequestPlayer> findByPlayer(Long playerId) {
        Optional<RequestPlayerEntity> optionalRequestPlayer = requestPlayerRepository.findByPlayer_Id(playerId);
        return optionalRequestPlayer.map(RequestPlayerDboMapper::toModel);
    }

    @Override
    public List<RequestPlayer> findByTeam(Long teamId) {
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
