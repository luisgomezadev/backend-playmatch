package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.domain.enums.StatusReservation;

public interface CountReservationUseCase {
    Long countReservationsByTeamAndStatus(Long teamId, StatusReservation status);
    Long countReservationsByFieldAndStatus(Long fieldId, StatusReservation status);
}
