package com.lgsoftworks.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.PlayerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerDboMapper {

    public static Player toModel(PlayerEntity entity) {
        if (entity == null) return null;
        Player player = new Player();
        player.setId(entity.getId());
        player.setFirstName(entity.getFirstName());
        player.setLastName(entity.getLastName());
        player.setCity(entity.getCity());
        player.setAge(entity.getAge());
        player.setCellphone(entity.getCellphone());
        player.setDocumentType(entity.getDocumentType());
        player.setDocumentNumber(entity.getDocumentNumber());
        player.setEmail(entity.getEmail());
        player.setPassword(entity.getPassword());
        player.setRole(entity.getRole());
        player.setImageUrl(entity.getImageUrl());
        player.setTeam(TeamDboMapper.toSimpleModel(entity.getTeam()));
        return player;
    }

    public static PlayerEntity toDbo(Player player) {
        if (player == null) return null;
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(player.getId());
        playerEntity.setFirstName(player.getFirstName());
        playerEntity.setLastName(player.getLastName());
        playerEntity.setCity(player.getCity());
        playerEntity.setAge(player.getAge());
        playerEntity.setCellphone(player.getCellphone());
        playerEntity.setDocumentType(player.getDocumentType());
        playerEntity.setDocumentNumber(player.getDocumentNumber());
        playerEntity.setEmail(player.getEmail());
        playerEntity.setPassword(player.getPassword());
        playerEntity.setRole(player.getRole());
        playerEntity.setImageUrl(player.getImageUrl());
        playerEntity.setTeam(TeamDboMapper.toSimpleDbo(player.getTeam()));
        return playerEntity;
    }

    public static Player toSimpleModel(PlayerEntity entity) {
        if (entity == null) return null;
        Player player = new Player();
        player.setId(entity.getId());
        player.setFirstName(entity.getFirstName());
        player.setLastName(entity.getLastName());
        player.setCity(entity.getCity());
        player.setAge(entity.getAge());
        player.setCellphone(entity.getCellphone());
        player.setDocumentType(entity.getDocumentType());
        player.setDocumentNumber(entity.getDocumentNumber());
        player.setEmail(entity.getEmail());
        player.setPassword(entity.getPassword());
        player.setRole(entity.getRole());
        return player;
    }

    public static PlayerEntity toSimpleDbo(Player player) {
        if (player == null) return null;
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(player.getId());
        playerEntity.setFirstName(player.getFirstName());
        playerEntity.setLastName(player.getLastName());
        playerEntity.setCity(player.getCity());
        playerEntity.setAge(player.getAge());
        playerEntity.setCellphone(player.getCellphone());
        playerEntity.setDocumentType(player.getDocumentType());
        playerEntity.setDocumentNumber(player.getDocumentNumber());
        playerEntity.setEmail(player.getEmail());
        playerEntity.setPassword(player.getPassword());
        playerEntity.setRole(player.getRole());
        return playerEntity;
    }

    public static List<Player> toModelList(List<PlayerEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(PlayerDboMapper::toSimpleModel)
                .collect(Collectors.toList());
    }

    public static List<PlayerEntity> toDboList(List<Player> players) {
        if (players == null) return null;
        return players.stream()
                .map(PlayerDboMapper::toSimpleDbo)
                .collect(Collectors.toList());
    }

}
