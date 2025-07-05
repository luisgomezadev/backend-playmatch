package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.User;

public class UserTypeNotAllowedToCreateTeamException extends RuntimeException {
    public UserTypeNotAllowedToCreateTeamException(User user) {
        super("El usuario con ID " + user.getId() + " no tiene permitido registrar un equipo");
    }
}
