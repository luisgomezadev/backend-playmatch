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
    public Long countReservationsByTeamAndStatus(Long teamId, StatusReservation status) {
        return reservationRepositoryPort.countReservationsByTeamAndStatus(status, teamId);
    }

    @Override
    public Long countReservationsByFieldAndStatus(Long fieldId, StatusReservation status) {
        return reservationRepositoryPort.countReservationsByFieldAndStatus(status, fieldId);
    }
}
