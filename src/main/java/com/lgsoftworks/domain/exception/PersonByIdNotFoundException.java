package com.lgsoftworks.domain.exception;

public class PersonByIdNotFoundException extends RuntimeException{
    public PersonByIdNotFoundException(Long id) {
        super("El usuario con ID " + id + " no se encuentra en el sistema");
    }
}
