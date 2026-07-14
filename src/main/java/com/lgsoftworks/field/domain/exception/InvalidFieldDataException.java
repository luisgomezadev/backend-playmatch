package com.lgsoftworks.field.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class InvalidFieldDataException extends DomainException {
    public InvalidFieldDataException(String message) {
        super(message);
    }
}