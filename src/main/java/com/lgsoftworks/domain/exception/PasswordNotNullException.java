package com.lgsoftworks.domain.exception;

public class PasswordNotNullException extends RuntimeException {
    public PasswordNotNullException() {
        super("La contraseña no puede estar vacía");
    }
}