package com.lgsoftworks.domain.exception;

public class UserWithDocumentExistsException extends RuntimeException{
    public UserWithDocumentExistsException(String documentNumber){
        super("Un usuario con el documento " + documentNumber + " ya está registrado en el sistema");
    }
}
