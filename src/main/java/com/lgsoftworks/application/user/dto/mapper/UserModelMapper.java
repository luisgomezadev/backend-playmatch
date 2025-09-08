package com.lgsoftworks.application.user.dto.mapper;

import com.lgsoftworks.application.user.dto.response.UserDTO;
import com.lgsoftworks.application.user.dto.request.UserRequest;
import com.lgsoftworks.domain.user.model.User;

import java.util.Objects;

public class UserModelMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setFullName(user.getFullName());
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
        user.setFullName(userDTO.getFullName());
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
        user.setFullName(buildFullName(userRequest.getFirstName(), userRequest.getLastName()));
        user.setCity(userRequest.getCity());
        user.setCellphone(userRequest.getCellphone());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        return user;
    }

    private static String buildFullName(String firstName, String lastName) {
        return (Objects.toString(firstName, "") + " " + Objects.toString(lastName, "")).trim();
    }

}
