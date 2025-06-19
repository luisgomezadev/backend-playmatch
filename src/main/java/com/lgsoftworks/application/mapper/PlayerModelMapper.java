package com.lgsoftworks.application.mapper;

import com.lgsoftworks.infrastructure.rest.dto.PlayerDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.PlayerRequest;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.infrastructure.rest.dto.summary.PlayerSummaryDTO;

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
        playerDTO.setTeam(TeamModelMapper.toTeamSummary(player.getTeam()));
        return playerDTO;
    }

    public static PlayerSummaryDTO toSummaryDTO(Player player) {
        PlayerSummaryDTO dto = new PlayerSummaryDTO();
        dto.setId(player.getId());
        dto.setFirstName(player.getFirstName());
        dto.setLastName(player.getLastName());
        dto.setCity(player.getCity());
        dto.setAge(player.getAge());
        dto.setCellphone(player.getCellphone());
        dto.setDocumentType(player.getDocumentType());
        dto.setDocumentNumber(player.getDocumentNumber());
        dto.setTeam(TeamModelMapper.toTeamSummary(player.getTeam()));
        return dto;
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
        player.setTeam(TeamModelMapper.dtoSummaryToModel(playerDTO.getTeam()));
        return player;
    }

    public static Player toModelRequest(PlayerRequest playerRequest) {
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
        player.setTeam(playerRequest.getTeam());
        player.setPassword(playerRequest.getPassword());
        player.setRole(Role.PLAYER);
        return player;
    }

}
