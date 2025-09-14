package com.lgsoftworks.domain.exception;

public class FieldByIdNotFoundException extends RuntimeException {
    public FieldByIdNotFoundException(Long id) {
      super("La cancha con ID " + id + " no se encuentra en el sistema");
    }
}
