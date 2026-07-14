package com.lgsoftworks.venue.domain.exception;

import com.lgsoftworks.shared.domain.exception.DomainException;

public class InvalidVenueDataException extends DomainException {
    public InvalidVenueDataException(String message) { super(message); }
}
