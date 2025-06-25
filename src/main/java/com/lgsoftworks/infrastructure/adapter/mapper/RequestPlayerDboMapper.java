package com.lgsoftworks.infrastructure.adapter.mapper;

import com.lgsoftworks.domain.model.TeamApplication;
import com.lgsoftworks.infrastructure.adapter.entity.RequestPlayerEntity;

public class RequestPlayerDboMapper {

    public static TeamApplication toModel(RequestPlayerEntity requestPlayerEntity) {
        if (requestPlayerEntity == null) return null;
        TeamApplication teamApplication = new TeamApplication();
        teamApplication.setId(requestPlayerEntity.getId());
        teamApplication.setDescription(requestPlayerEntity.getDescription());
        teamApplication.setPlayer(PlayerDboMapper.toSimpleModel(requestPlayerEntity.getPlayer()));
        teamApplication.setTeam(TeamDboMapper.toSimpleModel(requestPlayerEntity.getTeam()));
        teamApplication.setApplicationDate(requestPlayerEntity.getRequestDate());
        teamApplication.setStatusRequest(requestPlayerEntity.getStatusRequest());
        return teamApplication;
    }

    public static RequestPlayerEntity toDbo(TeamApplication teamApplication) {
        if (teamApplication == null) return null;
        RequestPlayerEntity requestPlayerEntity = new RequestPlayerEntity();
        requestPlayerEntity.setId(teamApplication.getId());
        requestPlayerEntity.setDescription(teamApplication.getDescription());
        requestPlayerEntity.setPlayer(PlayerDboMapper.toSimpleDbo(teamApplication.getPlayer()));
        requestPlayerEntity.setTeam(TeamDboMapper.toSimpleDbo(teamApplication.getTeam()));
        requestPlayerEntity.setRequestDate(teamApplication.getApplicationDate());
        requestPlayerEntity.setStatusRequest(teamApplication.getStatusRequest());
        return requestPlayerEntity;
    }

}
