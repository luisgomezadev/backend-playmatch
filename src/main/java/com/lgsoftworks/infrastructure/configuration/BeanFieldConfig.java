package com.lgsoftworks.infrastructure.configuration;

import com.lgsoftworks.application.service.FieldService;
import com.lgsoftworks.domain.port.in.FieldUseCase;
import com.lgsoftworks.domain.port.out.AdminRepositoryPort;
import com.lgsoftworks.domain.port.out.FieldRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFieldConfig {

    @Bean
    FieldUseCase fieldBeanUseCase(FieldRepositoryPort fieldRepositoryPort,
                                  AdminRepositoryPort adminRepositoryPort) {
        return new FieldService(fieldRepositoryPort, adminRepositoryPort);
    }
}
