package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.model.Team;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Player player, Team team) {
        super(player.getFirstName() + " " + player.getLastName() + " no se encuentra en el equipo " + team.getName());
    }
}
