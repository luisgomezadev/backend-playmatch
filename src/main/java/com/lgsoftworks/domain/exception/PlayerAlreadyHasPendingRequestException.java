package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.model.Team;

public class PlayerAlreadyHasPendingRequestException extends RuntimeException {
    public PlayerAlreadyHasPendingRequestException(Player player, Team team) {
        super(player.getFirstName() + " " + player.getLastName() +
                " ya tiene una solicitud pendiente con el equipo " +
                team.getName());
    }
}
