package com.lgsoftworks.domain.port.in;

public interface AssignTeamUseCase {
    void addMemberToTeam(Long teamId, Long playerId);
}
