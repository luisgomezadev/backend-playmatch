package com.lgsoftworks.field.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class DuplicateFieldNameException extends DomainException {
    public DuplicateFieldNameException(String fieldName, Long venueId) {
        super("Ya existe una cancha con el nombre '" + fieldName + "' en el complejo con id " + venueId);
    }
}