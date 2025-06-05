package com.lgsoftworks.domain.exception;

public class RequestPlayerByIdNotFoundException extends RuntimeException {
    public RequestPlayerByIdNotFoundException(Long id) {
        super("La solicitud con ID " + id + " no se encuentra en el sistema");
    }
}
