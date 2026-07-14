package com.lgsoftworks.reservation.application.port.in;

import com.lgsoftworks.reservation.application.dto.request.ReservationRequest;
import com.lgsoftworks.reservation.application.dto.response.ReservationDTO;
import com.lgsoftworks.reservation.application.dto.response.TimeSlot;

import java.time.LocalDate;
import java.util.List;

public interface ReservationAvailabilityUseCase {
    List<TimeSlot> getAvailableSlots(Long venueId, Long fieldId, LocalDate date);
    ReservationDTO reservationAvailability(ReservationRequest reservationRequest);
}