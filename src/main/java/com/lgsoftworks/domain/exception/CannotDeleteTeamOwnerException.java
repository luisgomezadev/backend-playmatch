package com.lgsoftworks.domain.exception;

public class CannotDeleteTeamOwnerException extends RuntimeException {
    public CannotDeleteTeamOwnerException() {
        super("No se puede eliminar al dueño del equipo.");
    }
}
