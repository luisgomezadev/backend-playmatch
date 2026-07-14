package com.lgsoftworks.venue.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class VenueByCodeNotFoundException extends DomainException {
    public VenueByCodeNotFoundException(String code) {
        super("Complejo deportivo no encontrado con código: " + code);
    }
}