package com.lgsoftworks.application.service;

import com.lgsoftworks.domain.exception.*;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.model.Team;
import com.lgsoftworks.domain.port.in.DeletePlayerTeamUseCase;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.domain.port.out.TeamRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeletePlayerTeamService implements DeletePlayerTeamUseCase {

    private final PlayerRepositoryPort playerRepositoryPort;
    private final TeamRepositoryPort teamRepositoryPort;

    @Override
    public void deletePlayerOfTeam(Long teamId, Long playerId) {
        Player player = playerRepositoryPort.findById(playerId)
                .orElseThrow(() -> new PersonByIdNotFoundException(playerId));

        Team team = teamRepositoryPort.findById(teamId)
                .orElseThrow(() -> new TeamByIdNotFoundException(teamId));

        validateDeletePlayerOfTeam(team, player);
        player.setTeam(null);
        playerRepositoryPort.save(player);
    }

    public void validateDeletePlayerOfTeam(Team team, Player player){
        if (!playerRepositoryPort.existsByIdAndTeamId(player.getId(), team.getId())) {
            throw new PlayerNotFoundException(player, team);
        }

        if (Objects.equals(player.getId(), team.getOwnerId())) {
            throw new CannotDeleteTeamOwnerException();
        }
    }
}
