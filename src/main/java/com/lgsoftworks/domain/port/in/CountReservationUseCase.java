package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.domain.enums.StatusReservation;

public interface CountReservationUseCase {
    Long countReservationsByUserAndStatus(Long userId, StatusReservation status);
    Long countReservationsByFieldAndStatus(Long fieldId, StatusReservation status);
}
