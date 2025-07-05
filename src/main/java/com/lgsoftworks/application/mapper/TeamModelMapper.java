package com.lgsoftworks.application.mapper;

import com.lgsoftworks.application.dto.TeamDTO;
import com.lgsoftworks.application.dto.request.TeamRequest;
import com.lgsoftworks.application.dto.summary.TeamSummaryDTO;
import com.lgsoftworks.domain.model.Team;

public class TeamModelMapper {

    public static TeamSummaryDTO toTeamSummary(Team team) {
        if (team == null) return null;
        TeamSummaryDTO teamSummaryDTO = new TeamSummaryDTO();
        teamSummaryDTO.setId(team.getId());
        teamSummaryDTO.setName(team.getName());
        teamSummaryDTO.setCity(team.getCity());
        teamSummaryDTO.setNeighborhood(team.getNeighborhood());
        teamSummaryDTO.setMaxPlayers(team.getMaxPlayers());
        teamSummaryDTO.setOwnerId(team.getOwnerId());
        teamSummaryDTO.setImageUrl(team.getImageUrl());
        return teamSummaryDTO;
    }

    public static TeamDTO toDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setCity(team.getCity());
        teamDTO.setNeighborhood(team.getNeighborhood());
        teamDTO.setMembers(UserModelMapper.toUserSummaryList(team.getMembers()));
        teamDTO.setMaxPlayers(team.getMaxPlayers());
        teamDTO.setOwnerId(team.getOwnerId());
        teamDTO.setImageUrl(team.getImageUrl());
        teamDTO.setReservations(ReservationModelMapper.toReservationTeamDTOList(team.getReservations()));
        return teamDTO;
    }

    public static Team toModel(TeamDTO teamDTO) {
        if (teamDTO == null) return null;
        Team team = new Team();
        team.setId(teamDTO.getId());
        team.setName(teamDTO.getName());
        team.setCity(teamDTO.getCity());
        team.setNeighborhood(teamDTO.getNeighborhood());
        team.setMembers(UserModelMapper.toPlayerList(teamDTO.getMembers()));
        team.setMaxPlayers(teamDTO.getMaxPlayers());
        team.setOwnerId(teamDTO.getOwnerId());
        team.setImageUrl(teamDTO.getImageUrl());
        team.setReservations(ReservationModelMapper.toReservationTeamList(teamDTO.getReservations()));
        return team;
    }

    public static Team toModelRequest(TeamRequest teamRequest) {
        if (teamRequest == null) return null;
        Team team = new Team();
        team.setId(teamRequest.getId());
        team.setName(teamRequest.getName());
        team.setCity(teamRequest.getCity());
        team.setNeighborhood(teamRequest.getNeighborhood());
        team.setMaxPlayers(teamRequest.getMaxPlayers());
        team.setOwnerId(teamRequest.getOwnerId());
        team.setImageUrl(teamRequest.getImageUrl());
        return team;
    }

    public static Team dtoSummaryToModel(TeamSummaryDTO teamSummaryDTO) {
        if (teamSummaryDTO == null) return null;
        Team team = new Team();
        team.setId(teamSummaryDTO.getId());
        team.setName(teamSummaryDTO.getName());
        team.setCity(teamSummaryDTO.getCity());
        team.setNeighborhood(teamSummaryDTO.getNeighborhood());
        team.setMaxPlayers(teamSummaryDTO.getMaxPlayers());
        team.setOwnerId(teamSummaryDTO.getOwnerId());
        team.setImageUrl(teamSummaryDTO.getImageUrl());
        return team;
    }

}
