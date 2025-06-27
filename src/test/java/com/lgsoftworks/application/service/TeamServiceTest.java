package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.TeamModelMapper;
import com.lgsoftworks.domain.model.Team;
import com.lgsoftworks.domain.port.in.AssignTeamUseCase;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.domain.port.out.TeamRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepositoryPort teamRepositoryPort;

    @Mock
    private PlayerRepositoryPort playerRepositoryPort;

    @Mock
    private AssignTeamUseCase assignTeamUseCase;

    @InjectMocks
    private TeamService teamService;

    @Test
    void findAllTest() {
        Mockito.when(teamRepositoryPort.findAll())
                .thenReturn(Collections.singletonList(new Team()));

        List<Team> list = teamService.findAll().stream().map(TeamModelMapper::toModel).toList();

        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals(1, list.size());

        Mockito.verify(teamRepositoryPort, Mockito.times(1)).findAll();
    }

}
