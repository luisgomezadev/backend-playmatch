package com.lgsoftworks.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.domain.model.TeamApplication;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.TeamApplicationEntity;

public class TeamApplicationDboMapper {

    public static TeamApplication toModel(TeamApplicationEntity teamApplicationEntity) {
        if (teamApplicationEntity == null) return null;
        TeamApplication teamApplication = new TeamApplication();
        teamApplication.setId(teamApplicationEntity.getId());
        teamApplication.setDescription(teamApplicationEntity.getDescription());
        teamApplication.setPlayer(PlayerDboMapper.toSimpleModel(teamApplicationEntity.getPlayer()));
        teamApplication.setTeam(TeamDboMapper.toSimpleModel(teamApplicationEntity.getTeam()));
        teamApplication.setApplicationDate(teamApplicationEntity.getApplicationDate());
        teamApplication.setStatusRequest(teamApplicationEntity.getStatusRequest());
        return teamApplication;
    }

    public static TeamApplicationEntity toDbo(TeamApplication teamApplication) {
        if (teamApplication == null) return null;
        TeamApplicationEntity teamApplicationEntity = new TeamApplicationEntity();
        teamApplicationEntity.setId(teamApplication.getId());
        teamApplicationEntity.setDescription(teamApplication.getDescription());
        teamApplicationEntity.setPlayer(PlayerDboMapper.toSimpleDbo(teamApplication.getPlayer()));
        teamApplicationEntity.setTeam(TeamDboMapper.toSimpleDbo(teamApplication.getTeam()));
        teamApplicationEntity.setApplicationDate(teamApplication.getApplicationDate());
        teamApplicationEntity.setStatusRequest(teamApplication.getStatusRequest());
        return teamApplicationEntity;
    }

}
