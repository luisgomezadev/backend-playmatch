package com.lgsoftworks.field.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class FieldByIdNotFoundException extends DomainException {
    public FieldByIdNotFoundException(Long id) {
        super("La cancha con ID " + id + " no se encuentra en el sistema");
    }
}
