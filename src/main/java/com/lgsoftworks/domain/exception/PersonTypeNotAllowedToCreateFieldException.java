package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.Person;

public class PersonTypeNotAllowedToCreateFieldException extends RuntimeException {
    public PersonTypeNotAllowedToCreateFieldException(Person person) {
        super("La persona con ID " + person.getId() + " no tiene permitido registrar una cancha");
    }
}
