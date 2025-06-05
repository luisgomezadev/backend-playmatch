package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.Team;

public class TeamAlreadyHasReservationException extends RuntimeException {
    public TeamAlreadyHasReservationException(Team team) {
        super("El equipo " + team.getName() + " ya tiene una reserva activa");
    }
}
