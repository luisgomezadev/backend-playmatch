package com.lgsoftworks.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.domain.user.model.User;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.UserEntity;

public class UserDboMapper {

    public static User toModel(UserEntity entity) {
        if (entity == null) return null;
        User user = new User();
        user.setId(entity.getId());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setFullName(entity.getFullName());
        user.setCity(entity.getCity());
        user.setCellphone(entity.getCellphone());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setRole(entity.getRole());
        user.setImageUrl(entity.getImageUrl());
        return user;
    }

    public static UserEntity toDbo(User model) {
        if (model == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setFullName(model.getFullName());
        entity.setCity(model.getCity());
        entity.setCellphone(model.getCellphone());
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getPassword());
        entity.setRole(model.getRole());
        entity.setImageUrl(model.getImageUrl());
        return entity;
    }

}
