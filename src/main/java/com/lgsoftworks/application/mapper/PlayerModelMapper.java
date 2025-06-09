package com.lgsoftworks.application.mapper;

import com.lgsoftworks.infrastructure.rest.dto.PlayerDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.PlayerRequest;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.model.Player;

public class PlayerModelMapper {

    public static PlayerDTO toDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setFirstName(player.getFirstName());
        playerDTO.setLastName(player.getLastName());
        playerDTO.setCity(player.getCity());
        playerDTO.setAge(player.getAge());
        playerDTO.setDocumentType(player.getDocumentType());
        playerDTO.setDocumentNumber(player.getDocumentNumber());
        playerDTO.setEmail(player.getEmail());
        playerDTO.setTeam(TeamModelMapper.toTeamSummary(player.getTeam()));
        return playerDTO;
    }

    public static Player toModel(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setFirstName(playerDTO.getFirstName());
        player.setLastName(playerDTO.getLastName());
        player.setCity(playerDTO.getCity());
        player.setAge(playerDTO.getAge());
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
        player.setDocumentType(playerRequest.getDocumentType());
        player.setDocumentNumber(playerRequest.getDocumentNumber());
        player.setEmail(playerRequest.getEmail());
        player.setTeam(playerRequest.getTeam());
        player.setPassword(playerRequest.getPassword());
        player.setRole(Role.PLAYER);
        return player;
    }

}
