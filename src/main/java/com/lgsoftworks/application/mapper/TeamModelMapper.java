package com.lgsoftworks.application.mapper;

import com.lgsoftworks.application.dto.TeamDTO;
import com.lgsoftworks.application.dto.request.TeamRequest;
import com.lgsoftworks.application.dto.summary.TeamSummaryDTO;
import com.lgsoftworks.domain.model.Team;

public class TeamModelMapper {

    public static TeamSummaryDTO toTeamSummary(Team team) {
        if (team == null) return null;
        TeamSummaryDTO dto = new TeamSummaryDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setCity(team.getCity());
        dto.setNeighborhood(team.getNeighborhood());
        dto.setMaxPlayers(team.getMaxPlayers());
        dto.setOwnerId(team.getOwnerId());
        return dto;
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
        return team;
    }

}
