package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.User;
import com.lgsoftworks.domain.model.Team;

public class PlayerAlreadyMemberOfTeamException extends RuntimeException {
    public PlayerAlreadyMemberOfTeamException(User user, Team team) {
        super(user.getFirstName() + " " + user.getLastName() + " ya está en el equipo de " + team.getName());
    }
}
