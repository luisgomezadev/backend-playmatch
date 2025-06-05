package com.lgsoftworks.domain.exception;

public class PersonWithEmailExistsException extends RuntimeException {
    public PersonWithEmailExistsException(String email) {
      super("Una persona con el email " + email + " ya está registrado en el sistema");
    }
}
