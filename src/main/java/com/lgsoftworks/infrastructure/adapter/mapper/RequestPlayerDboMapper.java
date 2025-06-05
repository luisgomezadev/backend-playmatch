package com.lgsoftworks.infrastructure.adapter.mapper;

import com.lgsoftworks.domain.model.RequestPlayer;
import com.lgsoftworks.infrastructure.adapter.entity.RequestPlayerEntity;

public class RequestPlayerDboMapper {

    public static RequestPlayer toModel(RequestPlayerEntity requestPlayerEntity) {
        if (requestPlayerEntity == null) return null;
        RequestPlayer requestPlayer = new RequestPlayer();
        requestPlayer.setId(requestPlayerEntity.getId());
        requestPlayer.setDescription(requestPlayerEntity.getDescription());
        requestPlayer.setPlayer(PlayerDboMapper.toSimpleModel(requestPlayerEntity.getPlayer()));
        requestPlayer.setTeam(TeamDboMapper.toSimpleModel(requestPlayerEntity.getTeam()));
        requestPlayer.setRequestDate(requestPlayerEntity.getRequestDate());
        requestPlayer.setStatusRequest(requestPlayerEntity.getStatusRequest());
        return requestPlayer;
    }

    public static RequestPlayerEntity toDbo(RequestPlayer requestPlayer) {
        if (requestPlayer == null) return null;
        RequestPlayerEntity requestPlayerEntity = new RequestPlayerEntity();
        requestPlayerEntity.setId(requestPlayer.getId());
        requestPlayerEntity.setDescription(requestPlayer.getDescription());
        requestPlayerEntity.setPlayer(PlayerDboMapper.toSimpleDbo(requestPlayer.getPlayer()));
        requestPlayerEntity.setTeam(TeamDboMapper.toSimpleDbo(requestPlayer.getTeam()));
        requestPlayerEntity.setRequestDate(requestPlayer.getRequestDate());
        requestPlayerEntity.setStatusRequest(requestPlayer.getStatusRequest());
        return requestPlayerEntity;
    }

}
