package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.user.model.User;

public class UserTypeNotAllowedToCreateFieldException extends RuntimeException {
    public UserTypeNotAllowedToCreateFieldException(User user) {
        super("El usuario con ID " + user.getId() + " no tiene permitido registrar una cancha");
    }
}
