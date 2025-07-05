package com.lgsoftworks.application.mapper;

import com.lgsoftworks.application.dto.PlayerDTO;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.application.dto.summary.PlayerSummaryDTO;

public class PlayerModelMapper {

    public static PlayerDTO toDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setFirstName(player.getFirstName());
        playerDTO.setLastName(player.getLastName());
        playerDTO.setCity(player.getCity());
        playerDTO.setAge(player.getAge());
        playerDTO.setCellphone(player.getCellphone());
        playerDTO.setDocumentType(player.getDocumentType());
        playerDTO.setDocumentNumber(player.getDocumentNumber());
        playerDTO.setEmail(player.getEmail());
        playerDTO.setImageUrl(player.getImageUrl());
        playerDTO.setTeam(TeamModelMapper.toTeamSummary(player.getTeam()));
        return playerDTO;
    }

    public static PlayerSummaryDTO toSummaryDTO(Player player) {
        PlayerSummaryDTO playerSummaryDTO = new PlayerSummaryDTO();
        playerSummaryDTO.setId(player.getId());
        playerSummaryDTO.setFirstName(player.getFirstName());
        playerSummaryDTO.setLastName(player.getLastName());
        playerSummaryDTO.setCity(player.getCity());
        playerSummaryDTO.setAge(player.getAge());
        playerSummaryDTO.setCellphone(player.getCellphone());
        playerSummaryDTO.setDocumentType(player.getDocumentType());
        playerSummaryDTO.setDocumentNumber(player.getDocumentNumber());
        playerSummaryDTO.setImageUrl(player.getImageUrl());
        playerSummaryDTO.setTeam(TeamModelMapper.toTeamSummary(player.getTeam()));
        return playerSummaryDTO;
    }

    public static Player toModel(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setFirstName(playerDTO.getFirstName());
        player.setLastName(playerDTO.getLastName());
        player.setCity(playerDTO.getCity());
        player.setAge(playerDTO.getAge());
        player.setCellphone(playerDTO.getCellphone());
        player.setDocumentType(playerDTO.getDocumentType());
        player.setDocumentNumber(playerDTO.getDocumentNumber());
        player.setEmail(playerDTO.getEmail());
        player.setImageUrl(playerDTO.getImageUrl());
        player.setTeam(TeamModelMapper.dtoSummaryToModel(playerDTO.getTeam()));
        return player;
    }

    public static Player summaryToModel(PlayerSummaryDTO playerSummaryDTO) {
        Player player = new Player();
        player.setId(playerSummaryDTO.getId());
        player.setFirstName(playerSummaryDTO.getFirstName());
        player.setLastName(playerSummaryDTO.getLastName());
        player.setCity(playerSummaryDTO.getCity());
        player.setAge(playerSummaryDTO.getAge());
        player.setCellphone(playerSummaryDTO.getCellphone());
        player.setDocumentType(playerSummaryDTO.getDocumentType());
        player.setDocumentNumber(playerSummaryDTO.getDocumentNumber());
        player.setImageUrl(playerSummaryDTO.getImageUrl());
        player.setTeam(TeamModelMapper.dtoSummaryToModel(playerSummaryDTO.getTeam()));
        return player;
    }

    public static Player toModelRequest(UserRequest playerRequest) {
        Player player = new Player();
        player.setId(playerRequest.getId());
        player.setFirstName(playerRequest.getFirstName());
        player.setLastName(playerRequest.getLastName());
        player.setCity(playerRequest.getCity());
        player.setAge(playerRequest.getAge());
        player.setCellphone(playerRequest.getCellphone());
        player.setDocumentType(playerRequest.getDocumentType());
        player.setDocumentNumber(playerRequest.getDocumentNumber());
        player.setEmail(playerRequest.getEmail());
        player.setPassword(playerRequest.getPassword());
        player.setRole(Role.PLAYER);
        return player;
    }

}
