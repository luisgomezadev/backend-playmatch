package com.lgsoftworks.domain.exception;

public class PersonWithDocumentExistsException extends RuntimeException{
    public PersonWithDocumentExistsException(String documentNumber){
        super("Una persona con el documento " + documentNumber + " ya está registrado en el sistema");
    }
}
