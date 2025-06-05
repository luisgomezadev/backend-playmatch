package com.lgsoftworks.domain.exception;

public class ReservationByIdNotFoundException extends RuntimeException {
    public ReservationByIdNotFoundException(Long id) {
        super("La reserva con ID " + id + " no se encuentra en el sistema");
    }
}
