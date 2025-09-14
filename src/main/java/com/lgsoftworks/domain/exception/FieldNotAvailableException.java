package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.field.model.Field;

public class FieldNotAvailableException extends RuntimeException {
    public FieldNotAvailableException(Field field) {
        super(field.getName() + " de " + field.getVenue().getName() + " ya está reservada para esa fecha y hora");
    }
}
