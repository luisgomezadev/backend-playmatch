package com.lgsoftworks.venue.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class VenueByAdminIdNotFoundException extends DomainException {
    public VenueByAdminIdNotFoundException(Long adminId) {
        super("No se encontró un complejo deportivo para el administrador con id: " + adminId);
    }
}