package com.lgsoftworks.domain.exception;

public class TeamCapacityBelowCurrentPlayersException extends RuntimeException {
  public TeamCapacityBelowCurrentPlayersException(int currentPlayers, int newLimit) {
    super("El nuevo límite de jugadores (" + newLimit +
            ") no puede ser menor que la cantidad actual de jugadores (" + currentPlayers + ").");
  }
}
