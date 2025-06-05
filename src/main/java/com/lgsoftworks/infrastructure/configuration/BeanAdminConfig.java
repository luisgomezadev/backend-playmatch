package com.lgsoftworks.infrastructure.configuration;

import com.lgsoftworks.application.service.AdminService;
import com.lgsoftworks.domain.port.in.AdminUseCase;
import com.lgsoftworks.domain.port.out.AdminRepositoryPort;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanAdminConfig {
    @Bean
    AdminUseCase adminBeanUseCase(AdminRepositoryPort adminRepositoryPort,
                                  PlayerRepositoryPort playerRepositoryPort) {
        return new AdminService(adminRepositoryPort, playerRepositoryPort);
    }
}
