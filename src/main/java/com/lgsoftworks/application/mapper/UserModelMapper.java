package com.lgsoftworks.application.mapper;

import com.lgsoftworks.infrastructure.rest.dto.UserDTO;
import com.lgsoftworks.domain.model.Admin;
import com.lgsoftworks.domain.model.User;
import com.lgsoftworks.domain.model.Player;

import java.util.List;
import java.util.stream.Collectors;

public class UserModelMapper {

    public static UserDTO toPersonSummary(User user) {
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setCity(user.getCity());
        dto.setAge(user.getAge());
        dto.setCellphone(user.getCellphone());
        dto.setDocumentType(user.getDocumentType());
        dto.setDocumentNumber(user.getDocumentNumber());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static Admin toAdmin(UserDTO userDTO) {
        if (userDTO == null) return null;
        Admin admin = new Admin();
        admin.setId(userDTO.getId());
        admin.setFirstName(userDTO.getFirstName());
        admin.setLastName(userDTO.getLastName());
        admin.setCity(userDTO.getCity());
        admin.setAge(userDTO.getAge());
        admin.setCellphone(userDTO.getCellphone());
        admin.setDocumentType(userDTO.getDocumentType());
        admin.setDocumentNumber(userDTO.getDocumentNumber());
        admin.setEmail(userDTO.getEmail());
        return admin;
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
        return player;
    }

    public static List<UserDTO> toPersonSummaryList(List<Player> persons) {
        if (persons == null) return null;
        return persons.stream()
                .map(UserModelMapper::toPersonSummary)
                .collect(Collectors.toList());
    }

    public static List<Player> toPlayerList(List<UserDTO> persons) {
        if (persons == null) return null;
        return persons.stream()
                .map(UserModelMapper::toPlayer)
                .collect(Collectors.toList());
    }


}
