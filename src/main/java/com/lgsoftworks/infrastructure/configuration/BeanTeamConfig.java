package com.lgsoftworks.infrastructure.configuration;

import com.lgsoftworks.application.service.TeamService;
import com.lgsoftworks.domain.port.in.AssignTeamUseCase;
import com.lgsoftworks.domain.port.in.TeamUseCase;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.domain.port.out.TeamRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanTeamConfig {

    @Bean
    TeamUseCase teamBeanUseCase(TeamRepositoryPort teamRepositoryPort,
                                PlayerRepositoryPort playerRepositoryPort,
                                AssignTeamUseCase assignTeamUseCase) {
        return new TeamService(teamRepositoryPort, playerRepositoryPort,
                assignTeamUseCase);
    }

}
