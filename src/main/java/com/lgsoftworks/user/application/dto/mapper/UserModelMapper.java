package com.lgsoftworks.user.application.dto.mapper;

import com.lgsoftworks.user.application.dto.request.UserRequest;
import com.lgsoftworks.user.application.dto.response.UserDTO;
import com.lgsoftworks.user.domain.model.User;

public class UserModelMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setFullName(user.getFullName());
        userDTO.setCellphone(user.getCellphone());
        userDTO.setEmail(user.getEmail());
        userDTO.setImageUrl(user.getImageUrl());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static User requestToModel(UserRequest request, String encodedPassword) {
        if (request == null) return null;
        return User.create(
                request.getFirstName(),
                request.getLastName(),
                request.getCellphone(),
                request.getEmail(),
                request.getPassword()
        );
    }

}
