package com.lgsoftworks.infrastructure.configuration;

import com.lgsoftworks.application.service.TeamApplicationService;
import com.lgsoftworks.domain.port.in.TeamApplicationUseCase;
import com.lgsoftworks.domain.port.out.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanRequestPlayerConfig {

    @Bean
    TeamApplicationUseCase requestPlayerBeanUseCase(TeamApplicationRepositoryPort teamApplicationRepositoryPort,
                                                    TeamRepositoryPort teamRepositoryPort,
                                                    PlayerRepositoryPort playerRepositoryPort) {
        return new TeamApplicationService(teamApplicationRepositoryPort,
                teamRepositoryPort, playerRepositoryPort);
    }
}
