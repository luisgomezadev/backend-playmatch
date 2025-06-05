package com.lgsoftworks.domain.exception;

import com.lgsoftworks.domain.model.Player;

public class PlayerAlreadyInTeamException extends RuntimeException {
  public PlayerAlreadyInTeamException(Player player) {
    super(player.getFirstName() + " " + player.getLastName() + " ya hace parte del equipo de " + player.getTeam().getName());
  }
}
