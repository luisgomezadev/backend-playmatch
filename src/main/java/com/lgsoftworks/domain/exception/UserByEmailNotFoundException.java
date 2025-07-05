package com.lgsoftworks.domain.exception;

public class UserByEmailNotFoundException extends RuntimeException {
    public UserByEmailNotFoundException(String email) {
        super("El usuario con email " + email + " no se encuentra registrado, verifique su tipo de usuario.");
    }
}
