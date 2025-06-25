package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.request.ReservationRequest;
import com.lgsoftworks.application.dto.summary.ReservationAvailabilityDTO;

import java.util.Optional;

public interface ReservationAvailabilityUseCase {
    Optional<ReservationAvailabilityDTO> reservationAvailability(ReservationRequest reservationRequest);
}
