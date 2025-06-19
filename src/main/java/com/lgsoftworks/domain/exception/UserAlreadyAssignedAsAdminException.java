package com.lgsoftworks.domain.exception;

public class UserAlreadyAssignedAsAdminException extends RuntimeException {
    public UserAlreadyAssignedAsAdminException(Long userId) {
        super("El usuario con ID " + userId + " ya es administrador de otra cancha.");
    }
}
