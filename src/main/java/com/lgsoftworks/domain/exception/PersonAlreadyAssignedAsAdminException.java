package com.lgsoftworks.domain.exception;

public class PersonAlreadyAssignedAsAdminException extends RuntimeException {
    public PersonAlreadyAssignedAsAdminException(Long personId) {
        super("El usuario con ID " + personId + " ya es administrador de otro campo.");
    }
}
