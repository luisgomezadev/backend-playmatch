package com.lgsoftworks.infrastructure.configuration;

import com.lgsoftworks.application.service.ReservationService;
import com.lgsoftworks.domain.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.port.in.ReservationUseCase;
import com.lgsoftworks.domain.port.out.FieldRepositoryPort;
import com.lgsoftworks.domain.port.out.ReservationRepositoryPort;
import com.lgsoftworks.domain.port.out.TeamRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanReservationConfig {
    @Bean
    ReservationUseCase reservationBeanUseCase(ReservationRepositoryPort reservationRepositoryPort,
                                              FieldRepositoryPort fieldRepositoryPort,
                                              TeamRepositoryPort teamRepositoryPort,
                                              ReservationAvailabilityUseCase reservationAvailabilityUseCase) {
        return new ReservationService(reservationRepositoryPort, fieldRepositoryPort, teamRepositoryPort,
                reservationAvailabilityUseCase);
    }

}
