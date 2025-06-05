package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.Person;

public class PersonTypeNotAllowedToCreateTeamException extends RuntimeException {
    public PersonTypeNotAllowedToCreateTeamException(Person person) {
        super("La persona con ID " + person.getId() + " no tiene permitido registrar un equipo");
    }
}
