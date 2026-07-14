package com.lgsoftworks.user.domain.exception;

public class UserByIdNotFoundException extends RuntimeException {
    public UserByIdNotFoundException(Long id) {
        super("El usuario con ID " + id + " no se encuentra en el sistema");
    }
}
