package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.User;

public class DuplicateOwnerException extends RuntimeException {
    public DuplicateOwnerException(User user) {
        super(user.getFirstName() + " " + user.getLastName() + " con ID " + user.getId() + " ya tiene un equipo registrado");
    }
}
