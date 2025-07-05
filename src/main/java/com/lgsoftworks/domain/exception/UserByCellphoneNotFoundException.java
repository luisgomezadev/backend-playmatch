package com.lgsoftworks.domain.exception;

public class UserByCellphoneNotFoundException extends RuntimeException {
    public UserByCellphoneNotFoundException(String cellphone) {
        super("El usuario con número de celular " + cellphone + " no se encuentra registrado");
    }
}
