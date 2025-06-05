package com.lgsoftworks.domain.exception;

public class PersonByIdNotFoundException extends RuntimeException{
    public PersonByIdNotFoundException(Long id) {
        super("La persona con ID " + id + " no se encuentra en el sistema");
    }
}
