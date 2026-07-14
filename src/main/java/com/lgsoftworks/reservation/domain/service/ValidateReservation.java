package com.lgsoftworks.reservation.domain.service;

import com.lgsoftworks.field.domain.exception.FieldNotAvailableException;
import com.lgsoftworks.reservation.domain.exception.ReservationTimeOutOfRangeException;
import com.lgsoftworks.reservation.domain.model.Reservation;
import com.lgsoftworks.reservation.domain.model.ReservationStatus;
import com.lgsoftworks.venue.domain.model.Venue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidateReservation {

    private ValidateReservation() {
    }

    public void validateTimeWithinVenueSchedule(Reservation reservation, Venue venue) {
        if (reservation.getStartTime().isBefore(venue.getOpeningHour())
                || reservation.getStartTime().isAfter(venue.getClosingHour())) {
            throw new ReservationTimeOutOfRangeException(
                    "La hora de inicio está fuera del horario del complejo deportivo");
        }
        if (reservation.getEndTime().isAfter(venue.getClosingHour())) {
            throw new ReservationTimeOutOfRangeException(
                    "La hora de finalización está fuera del horario del complejo deportivo");
        }
    }

    public void validateFieldAvailability(Reservation reservation, List<Reservation> existingReservations) {
        for (Reservation existing : existingReservations) {
            if (existing.getStatus() != ReservationStatus.ACTIVE) continue;
            if (reservation.overlapsWith(existing)) {
                throw new FieldNotAvailableException(reservation.getFieldId());
            }
        }
    }
}