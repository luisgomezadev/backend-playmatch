package com.lgsoftworks.domain.exception;

public class UserWithCellphoneExistsException extends RuntimeException {
    public UserWithCellphoneExistsException(String cellphone) {
      super("Un usuario con número de celular " + cellphone + " ya está registrado en el sistema");
    }
}
