package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.domain.model.Reservation;
import com.lgsoftworks.infrastructure.rest.dto.ReservationDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.ReservationRequest;
import com.lgsoftworks.infrastructure.rest.dto.summary.ReservationAvailabilityDTO;

import java.util.Optional;

public interface ReservationAvailabilityUseCase {
    Optional<ReservationAvailabilityDTO> reservationAvailability(ReservationRequest reservationRequest);
}
