package com.lgsoftworks.domain.exception;

public class UserByDocumentNotFoundException extends RuntimeException{
    public UserByDocumentNotFoundException(String documentNumber){
        super("El usuario con número de documento " + documentNumber + " no se encuentra registrado");
    }
}
