package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.User;

public class UserAlreadyHasReservationException extends RuntimeException {
    public UserAlreadyHasReservationException() {
        super("Ya tienes una reserva activa");
    }
}
