package com.lgsoftworks.infrastructure.adapter.mapper;

import com.lgsoftworks.domain.model.Team;
import com.lgsoftworks.infrastructure.adapter.entity.TeamEntity;

public class TeamDboMapper {
    public static Team toModel(TeamEntity teamEntity) {
        if (teamEntity == null) return null;
        Team team = new Team();
        team.setId(teamEntity.getId());
        team.setName(teamEntity.getName());
        team.setCity(teamEntity.getCity());
        team.setNeighborhood(teamEntity.getNeighborhood());
        team.setMembers(PlayerDboMapper.toModelList(teamEntity.getMembers()));
        team.setMaxPlayers(teamEntity.getMaxPlayers());
        team.setOwnerId(teamEntity.getOwnerId());
        team.setReservations(ReservationDboMapper.toModelList(teamEntity.getReservations()));
        return team;
    }

    public static TeamEntity toDbo(Team team) {
        if (team == null) return null;
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(team.getId());
        teamEntity.setName(team.getName());
        teamEntity.setCity(team.getCity());
        teamEntity.setNeighborhood(team.getNeighborhood());
        teamEntity.setMembers(PlayerDboMapper.toDboList(team.getMembers()));
        teamEntity.setMaxPlayers(team.getMaxPlayers());
        teamEntity.setOwnerId(team.getOwnerId());
        teamEntity.setReservations(ReservationDboMapper.toDboList(team.getReservations()));
        return teamEntity;
    }

    public static Team toSimpleModel(TeamEntity teamEntity) {
        if (teamEntity == null) return null;
        Team team = new Team();
        team.setId(teamEntity.getId());
        team.setName(teamEntity.getName());
        team.setCity(teamEntity.getCity());
        team.setNeighborhood(teamEntity.getNeighborhood());
        team.setMaxPlayers(teamEntity.getMaxPlayers());
        team.setOwnerId(teamEntity.getOwnerId());
        return team;
    }

    public static TeamEntity toSimpleDbo(Team team) {
        if (team == null) return null;
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(team.getId());
        teamEntity.setName(team.getName());
        teamEntity.setCity(team.getCity());
        teamEntity.setNeighborhood(team.getNeighborhood());
        teamEntity.setMaxPlayers(team.getMaxPlayers());
        teamEntity.setOwnerId(team.getOwnerId());
        return teamEntity;
    }

}
