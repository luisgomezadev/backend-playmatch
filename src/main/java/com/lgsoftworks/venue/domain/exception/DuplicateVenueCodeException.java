package com.lgsoftworks.venue.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class DuplicateVenueCodeException extends DomainException {
    public DuplicateVenueCodeException(String code) {
        super("Ya existe un complejo deportivo con el código: " + code);
    }
}