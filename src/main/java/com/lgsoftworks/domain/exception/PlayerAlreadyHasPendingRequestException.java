package com.lgsoftworks.domain.exception;

public class PlayerAlreadyHasPendingRequestException extends RuntimeException {
    public PlayerAlreadyHasPendingRequestException() {
        super("Ya tienes una solicitud pendiente");
    }
}
