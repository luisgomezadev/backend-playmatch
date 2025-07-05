package com.lgsoftworks.application.mapper;

import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.domain.model.FieldAdmin;
import com.lgsoftworks.domain.model.User;
import com.lgsoftworks.domain.model.Player;

import java.util.List;
import java.util.stream.Collectors;

public class UserModelMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setCity(user.getCity());
        userDTO.setAge(user.getAge());
        userDTO.setCellphone(user.getCellphone());
        userDTO.setDocumentType(user.getDocumentType());
        userDTO.setDocumentNumber(user.getDocumentNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setImageUrl(user.getImageUrl());
        return userDTO;
    }

    public static FieldAdmin toAdmin(UserDTO userDTO) {
        if (userDTO == null) return null;
        FieldAdmin fieldAdmin = new FieldAdmin();
        fieldAdmin.setId(userDTO.getId());
        fieldAdmin.setFirstName(userDTO.getFirstName());
        fieldAdmin.setLastName(userDTO.getLastName());
        fieldAdmin.setCity(userDTO.getCity());
        fieldAdmin.setAge(userDTO.getAge());
        fieldAdmin.setCellphone(userDTO.getCellphone());
        fieldAdmin.setDocumentType(userDTO.getDocumentType());
        fieldAdmin.setDocumentNumber(userDTO.getDocumentNumber());
        fieldAdmin.setEmail(userDTO.getEmail());
        fieldAdmin.setImageUrl(userDTO.getImageUrl());
        return fieldAdmin;
    }

    public static Player toPlayer(UserDTO userDTO) {
        if (userDTO == null) return null;
        Player player = new Player();
        player.setId(userDTO.getId());
        player.setFirstName(userDTO.getFirstName());
        player.setLastName(userDTO.getLastName());
        player.setCity(userDTO.getCity());
        player.setAge(userDTO.getAge());
        player.setCellphone(userDTO.getCellphone());
        player.setDocumentType(userDTO.getDocumentType());
        player.setDocumentNumber(userDTO.getDocumentNumber());
        player.setEmail(userDTO.getEmail());
        player.setImageUrl(userDTO.getImageUrl());
        return player;
    }

    public static List<UserDTO> toUserSummaryList(List<Player> players) {
        if (players == null) return null;
        return players.stream()
                .map(UserModelMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public static List<Player> toPlayerList(List<UserDTO> users) {
        if (users == null) return null;
        return users.stream()
                .map(UserModelMapper::toPlayer)
                .collect(Collectors.toList());
    }


}
