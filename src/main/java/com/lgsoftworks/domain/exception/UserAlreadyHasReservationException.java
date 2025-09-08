package com.lgsoftworks.domain.exception;

public class UserAlreadyHasReservationException extends RuntimeException {
    public UserAlreadyHasReservationException() {
        super("Ya tienes una reserva activa");
    }
}
