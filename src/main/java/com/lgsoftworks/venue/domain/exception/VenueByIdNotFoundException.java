package com.lgsoftworks.venue.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class VenueByIdNotFoundException extends DomainException {
    public VenueByIdNotFoundException(Long id) {
        super("Complejo deportivo no encontrado con id: " + id);
    }
}