package com.lgsoftworks.reservation.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class InvalidReservationDataException extends DomainException {
    public InvalidReservationDataException(String message) {
        super(message);
    }
}
