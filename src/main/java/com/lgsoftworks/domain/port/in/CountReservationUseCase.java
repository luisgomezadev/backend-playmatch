package com.lgsoftworks.domain.port.in;

public interface CountReservationUseCase {
    Long countActiveReservationsByTeam(Long teamId);
    Long countFinishedReservationsByTeam(Long teamId);
    Long countActiveReservationsByField(Long fieldId);
    Long countFinishedReservationsByField(Long fieldId);
}
