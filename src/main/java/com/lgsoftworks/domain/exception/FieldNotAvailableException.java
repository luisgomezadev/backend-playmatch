package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.Field;

public class FieldNotAvailableException extends RuntimeException {
    public FieldNotAvailableException(Field field) {
        super("El campo: " + field.getName() + " ya está reservado para esa fecha y hora");
    }
}
