package com.lgsoftworks.application.mapper;

import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.model.User;

public class UserModelMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setCity(user.getCity());
        userDTO.setCellphone(user.getCellphone());
        userDTO.setEmail(user.getEmail());
        userDTO.setImageUrl(user.getImageUrl());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static User toUser(UserDTO userDTO) {
        if (userDTO == null) return null;
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setCellphone(userDTO.getCellphone());
        user.setEmail(userDTO.getEmail());
        user.setImageUrl(userDTO.getImageUrl());
        user.setRole(userDTO.getRole());
        return user;
    }

    public static User requestToModel(UserRequest userRequest) {
        if (userRequest == null) return null;
        User user = new User();
        user.setId(userRequest.getId());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setCity(userRequest.getCity());
        user.setCellphone(userRequest.getCellphone());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        return user;
    }

    /*public static List<UserDTO> toUserSummaryList(List<Player> players) {
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
    }*/

}
