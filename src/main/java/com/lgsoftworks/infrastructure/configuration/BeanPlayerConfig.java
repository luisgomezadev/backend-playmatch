package com.lgsoftworks.infrastructure.configuration;

import com.lgsoftworks.application.service.PlayerService;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
import com.lgsoftworks.domain.port.out.AdminRepositoryPort;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPlayerConfig {

    @Bean
    PlayerUseCase playerBeanUseCase(PlayerRepositoryPort playerRepositoryPort,
                                    AdminRepositoryPort adminRepositoryPort) {
        return new PlayerService(playerRepositoryPort, adminRepositoryPort);
    }

}
