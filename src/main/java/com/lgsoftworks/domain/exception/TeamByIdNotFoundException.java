package com.lgsoftworks.domain.exception;

public class TeamByIdNotFoundException extends RuntimeException {
    public TeamByIdNotFoundException(Long id) {
        super("El equipo con ID " + id + " no se encuentra en el sistema");
    }
}
