package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.Team;

public class TeamFullException extends RuntimeException {
    public TeamFullException(Team team) {
        super("El equipo " + team.getName() + " solo permite tener " + team.getMaxPlayers() + " jugadores.");
    }
}
