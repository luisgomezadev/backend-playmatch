package com.lgsoftworks.user.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.user.domain.model.User;
import com.lgsoftworks.user.infrastructure.adapter.out.persistence.entity.UserEntity;

public class UserDboMapper {

    public static User toModel(UserEntity entity) {
        return User.rehydrate(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getFullName(),
                entity.getCellphone(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getImageUrl(),
                entity.getRole()
        );
    }

    public static UserEntity toDbo(User model) {
        if (model == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setFullName(model.getFullName());
        entity.setCellphone(model.getCellphone());
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getPassword());
        entity.setRole(model.getRole());
        entity.setImageUrl(model.getImageUrl());
        return entity;
    }

}
