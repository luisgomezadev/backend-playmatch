package com.lgsoftworks.application.mapper;

import com.lgsoftworks.infrastructure.rest.dto.RequestPlayerDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.RequestPlayerRequest;
import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.model.RequestPlayer;

import java.time.LocalDateTime;

public class RequestPlayerModelMapper {

    public static RequestPlayer toModel(RequestPlayerDTO requestPlayerDTO){
        RequestPlayer requestPlayer = new RequestPlayer();
        requestPlayer.setId(requestPlayerDTO.getId());
        requestPlayer.setDescription(requestPlayerDTO.getDescription());
        requestPlayer.setPlayer(PersonModelMapper.toPlayer(requestPlayerDTO.getPlayer()));
        requestPlayer.setTeam(TeamModelMapper.dtoSummaryToModel(requestPlayerDTO.getTeam()));
        requestPlayer.setRequestDate(requestPlayerDTO.getRequestDate());
        requestPlayer.setStatusRequest(requestPlayerDTO.getStatusRequest());
        return requestPlayer;
    }

    public static RequestPlayer toModelRequest(RequestPlayerRequest requestPlayerRequest) {
        RequestPlayer requestPlayer = new RequestPlayer();
        requestPlayer.setId(requestPlayerRequest.getId());
        requestPlayer.setDescription(requestPlayerRequest.getDescription());
        requestPlayer.setPlayer(requestPlayerRequest.getPlayer());
        requestPlayer.setTeam(requestPlayerRequest.getTeam());
        requestPlayer.setRequestDate(LocalDateTime.now());
        requestPlayer.setStatusRequest(StatusRequest.PENDING);
        return requestPlayer;
    }

    public static RequestPlayerDTO toDTO(RequestPlayer requestPlayer) {
        RequestPlayerDTO requestPlayerDTO = new RequestPlayerDTO();
        requestPlayerDTO.setId(requestPlayer.getId());
        requestPlayerDTO.setDescription(requestPlayer.getDescription());
        requestPlayerDTO.setPlayer(PersonModelMapper.toPersonSummary(requestPlayer.getPlayer()));
        requestPlayerDTO.setTeam(TeamModelMapper.toTeamSummary(requestPlayer.getTeam()));
        requestPlayerDTO.setRequestDate(requestPlayer.getRequestDate());
        requestPlayerDTO.setStatusRequest(requestPlayer.getStatusRequest());
        return requestPlayerDTO;
    }

}
