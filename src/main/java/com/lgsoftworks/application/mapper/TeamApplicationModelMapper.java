package com.lgsoftworks.application.mapper;

import com.lgsoftworks.application.dto.TeamApplicationDTO;
import com.lgsoftworks.application.dto.request.TeamApplicationRequest;
import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.model.TeamApplication;

import java.time.LocalDateTime;

public class TeamApplicationModelMapper {

    public static TeamApplication toModel(TeamApplicationDTO teamApplicationDTO){
        TeamApplication teamApplication = new TeamApplication();
        teamApplication.setId(teamApplicationDTO.getId());
        teamApplication.setDescription(teamApplicationDTO.getDescription());
        teamApplication.setPlayer(PlayerModelMapper.summaryToModel(teamApplicationDTO.getPlayer()));
        teamApplication.setTeam(TeamModelMapper.dtoSummaryToModel(teamApplicationDTO.getTeam()));
        teamApplication.setApplicationDate(teamApplicationDTO.getApplicationDate());
        teamApplication.setStatusRequest(teamApplicationDTO.getStatusRequest());
        return teamApplication;
    }

    public static TeamApplication toModelRequest(TeamApplicationRequest teamApplicationRequest) {
        TeamApplication teamApplication = new TeamApplication();
        teamApplication.setId(teamApplicationRequest.getId());
        teamApplication.setDescription(teamApplicationRequest.getDescription());
        teamApplication.setApplicationDate(LocalDateTime.now());
        teamApplication.setStatusRequest(StatusRequest.PENDING);
        return teamApplication;
    }

    public static TeamApplicationDTO toDTO(TeamApplication teamApplication) {
        TeamApplicationDTO teamApplicationDTO = new TeamApplicationDTO();
        teamApplicationDTO.setId(teamApplication.getId());
        teamApplicationDTO.setDescription(teamApplication.getDescription());
        teamApplicationDTO.setPlayer(PlayerModelMapper.toSummaryDTO(teamApplication.getPlayer()));
        teamApplicationDTO.setTeam(TeamModelMapper.toTeamSummary(teamApplication.getTeam()));
        teamApplicationDTO.setApplicationDate(teamApplication.getApplicationDate());
        teamApplicationDTO.setStatusRequest(teamApplication.getStatusRequest());
        return teamApplicationDTO;
    }

}
