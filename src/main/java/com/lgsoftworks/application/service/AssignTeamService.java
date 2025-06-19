package com.lgsoftworks.application.service;

import com.lgsoftworks.domain.exception.*;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.model.Team;
import com.lgsoftworks.domain.port.in.AssignTeamUseCase;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.domain.port.out.TeamRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignTeamService implements AssignTeamUseCase {

    private final PlayerRepositoryPort playerRepositoryPort;
    private final TeamRepositoryPort teamRepositoryPort;

    @Override
    public void addMemberToTeam(Long teamId, Long playerId) {
        Player player = playerRepositoryPort.findById(playerId)
                .orElseThrow(() -> new UserByIdNotFoundException(playerId));

        Team team = teamRepositoryPort.findById(teamId)
                .orElseThrow(() -> new TeamByIdNotFoundException(teamId));

        validateAddMemberToTeam(team, player);
        player.setTeam(team);

        playerRepositoryPort.save(player);
    }

    public void validateAddMemberToTeam(Team team, Player player){
        if (player.getTeam() != null) {
            throw new PlayerAlreadyInTeamException(player);
        }

        if (team.getMembers().stream().anyMatch(p -> p.getId().equals(player.getId()))) {
            throw new PlayerAlreadyMemberOfTeamException(player, team);
        }

        if (team.getMembers().size() >= team.getMaxPlayers()) {
            throw new TeamFullException(team);
        }
    }
}
