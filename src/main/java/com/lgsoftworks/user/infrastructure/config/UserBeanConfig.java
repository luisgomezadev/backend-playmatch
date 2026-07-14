package com.lgsoftworks.user.infrastructure.config;

import com.lgsoftworks.user.domain.port.out.UserRepositoryPort;
import com.lgsoftworks.user.domain.service.UserUniquenessValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfig {

    @Bean
    public UserUniquenessValidator userUniquenessValidator(UserRepositoryPort userRepositoryPort) {
        return new UserUniquenessValidator(userRepositoryPort);
    }
}
