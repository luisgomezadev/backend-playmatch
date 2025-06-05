package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.Person;
import com.lgsoftworks.domain.model.Team;

public class PersonAlreadyMemberOfTeamException extends RuntimeException {
    public PersonAlreadyMemberOfTeamException(Person person, Team team) {
        super(person.getFirstName() + " " + person.getLastName() + " ya está en el equipo de " + team.getName());
    }
}
