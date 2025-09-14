package com.lgsoftworks.domain.exception;

public class VenueByIdNotFoundException extends RuntimeException {
    public VenueByIdNotFoundException(Long id) {
        super("El complejo con ID " + id + " no se encuentra en el sistema");
    }
}
