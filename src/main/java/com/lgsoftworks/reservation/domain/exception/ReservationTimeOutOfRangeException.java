package com.lgsoftworks.reservation.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class ReservationTimeOutOfRangeException extends DomainException {
    public ReservationTimeOutOfRangeException(String message) {
        super(message);
    }
}