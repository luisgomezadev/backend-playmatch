package com.lgsoftworks.field.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class FieldNotAvailableException extends DomainException {
    public FieldNotAvailableException(Long fieldId) {
        super("La cancha con id " + fieldId + " no está disponible en el horario solicitado");
    }
}