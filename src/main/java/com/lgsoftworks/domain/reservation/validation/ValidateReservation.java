package com.lgsoftworks.domain.reservation.validation;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.exception.FieldNotAvailableException;
import com.lgsoftworks.domain.exception.ReservationTimeOutOfRangeException;
import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.domain.reservation.port.out.ReservationRepositoryPort;
import com.lgsoftworks.domain.venue.model.Venue;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ValidateReservation {

    private final ReservationRepositoryPort reservationRepositoryPort;

    public static void validateTimeWithinVenueSchedule(Reservation reservation, Venue venue) {
        LocalTime start = reservation.getStartTime();
        LocalTime end = reservation.getEndTime();

        if (start.isBefore(venue.getOpeningHour()) || start.isAfter(venue.getClosingHour())) {
            throw new ReservationTimeOutOfRangeException("La hora de inicio está fuera del horario del complejo");
        }

        if (end.isAfter(venue.getClosingHour())) {
            throw new ReservationTimeOutOfRangeException("La hora de finalizado está fuera del horario del complejo");
        }
    }

    public static void validateFieldAvailability(Reservation reservation, Field field) {
        List<Reservation> reservations = field.getReservations();
        for (Reservation r: reservations) {
            if (r.getStatus().equals(Status.ACTIVE) && Objects.equals(r.getField().getId(), field.getId())) {
                if (r.getReservationDate().equals(reservation.getReservationDate())) {
                    if ((reservation.getStartTime().isAfter(r.getStartTime()) && reservation.getStartTime().isBefore(r.getEndTime())) ||
                            (reservation.getEndTime().isAfter(r.getStartTime()) && reservation.getEndTime().isBefore(r.getEndTime())) ||
                            (reservation.getStartTime().equals(r.getStartTime()) || reservation.getEndTime().equals(r.getEndTime())) ||
                            (reservation.getStartTime().isBefore(r.getStartTime()) && reservation.getEndTime().isAfter(r.getEndTime()))
                    ) {
                        throw new FieldNotAvailableException(field);
                    }
                }
            }
        }
    }

}
