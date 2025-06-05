package com.lgsoftworks.domain.port.in;

public interface DeletePlayerTeamUseCase {
    void deletePlayerOfTeam(Long teamId, Long playerId);
}
