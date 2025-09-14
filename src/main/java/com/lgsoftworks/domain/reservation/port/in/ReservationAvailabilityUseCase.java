package com.lgsoftworks.domain.reservation.port.in;

import com.lgsoftworks.application.reservation.dto.response.ReservationDTO;
import com.lgsoftworks.application.reservation.dto.response.TimeSlot;
import com.lgsoftworks.application.reservation.dto.request.ReservationRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationAvailabilityUseCase {
    List<TimeSlot> getAvailableSlots(Long venueId, Long fieldId, LocalDate date);
    Optional<ReservationDTO> reservationAvailability(ReservationRequest reservationRequest);
}
