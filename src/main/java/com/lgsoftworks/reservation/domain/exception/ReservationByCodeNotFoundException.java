package com.lgsoftworks.reservation.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class ReservationByCodeNotFoundException extends DomainException {
    public ReservationByCodeNotFoundException(String code) {
        super("Reserva no encontrada con código: " + code);
    }
}