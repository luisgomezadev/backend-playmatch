package com.lgsoftworks.domain.exception;

public class TeamApplicationByIdNotFoundException extends RuntimeException {
    public TeamApplicationByIdNotFoundException(Long id) {
        super("La solicitud con ID " + id + " no se encuentra en el sistema");
    }
}
