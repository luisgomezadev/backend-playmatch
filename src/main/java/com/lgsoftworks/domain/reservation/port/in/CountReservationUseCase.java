package com.lgsoftworks.domain.reservation.port.in;


import com.lgsoftworks.domain.reservation.enums.StatusReservation;

public interface CountReservationUseCase {
    Long countReservationsByUserAndStatus(Long userId, StatusReservation status);
    Long countReservationsByFieldAndStatus(Long fieldId, StatusReservation status);
}
