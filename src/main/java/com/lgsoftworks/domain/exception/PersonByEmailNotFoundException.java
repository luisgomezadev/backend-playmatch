package com.lgsoftworks.domain.exception;

public class PersonByEmailNotFoundException extends RuntimeException {
    public PersonByEmailNotFoundException(String email) {
        super("El usuario con email (" + email + ") no se encuentra registrada.");
    }
}
