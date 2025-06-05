package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.Person;

public class DuplicateOwnerException extends RuntimeException {
    public DuplicateOwnerException(Person person) {
        super(person.getFirstName() + " " + person.getLastName() + " con ID " + person.getId() + " ya tiene un equipo registrado");
    }
}
