package com.lgsoftworks.application.service;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.port.in.CountReservationUseCase;
import com.lgsoftworks.domain.port.out.ReservationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountReservationService implements CountReservationUseCase {

    private final ReservationRepositoryPort reservationRepositoryPort;

    @Override
    public Long countActiveReservationsByTeam(Long teamId) {
        return reservationRepositoryPort.countActiveReservationsByTeam(StatusReservation.ACTIVE, teamId);
    }

    @Override
    public Long countFinishedReservationsByTeam(Long teamId) {
        return reservationRepositoryPort.countFinishedReservationsByTeam(StatusReservation.FINISHED, teamId);
    }

    @Override
    public Long countActiveReservationsByField(Long fieldId) {
        return reservationRepositoryPort.countActiveReservationsByField(StatusReservation.ACTIVE, fieldId);
    }

    @Override
    public Long countFinishedReservationsByField(Long fieldId) {
        return reservationRepositoryPort.countFinishedReservationsByField(StatusReservation.FINISHED, fieldId);
    }
}
