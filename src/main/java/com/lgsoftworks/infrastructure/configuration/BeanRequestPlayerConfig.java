package com.lgsoftworks.infrastructure.configuration;

import com.lgsoftworks.application.service.RequestPlayerService;
import com.lgsoftworks.domain.port.in.RequestPlayerUseCase;
import com.lgsoftworks.domain.port.out.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanRequestPlayerConfig {

    @Bean
    RequestPlayerUseCase requestPlayerBeanUseCase(RequestPlayerRepositoryPort requestPlayerRepositoryPort,
                                                  TeamRepositoryPort teamRepositoryPort,
                                                  PlayerRepositoryPort playerRepositoryPort) {
        return new RequestPlayerService(requestPlayerRepositoryPort,
                teamRepositoryPort, playerRepositoryPort);
    }
}
