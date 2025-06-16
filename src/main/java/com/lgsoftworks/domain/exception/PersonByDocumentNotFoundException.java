package com.lgsoftworks.domain.exception;

public class PersonByDocumentNotFoundException extends RuntimeException{
    public PersonByDocumentNotFoundException(String documentNumber){
        super("El usuario con número de documento " + documentNumber + " no se encuentra registrada");
    }
}
