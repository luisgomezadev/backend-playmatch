package com.lgsoftworks.domain.validation;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.exception.FieldNotAvailableException;
import com.lgsoftworks.domain.exception.ReservationTimeOutOfRangeException;
import com.lgsoftworks.domain.exception.UserAlreadyHasReservationException;
import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.domain.model.Reservation;
import com.lgsoftworks.domain.model.User;
import com.lgsoftworks.domain.port.out.ReservationRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ReservationValidator {

    private final ReservationRepositoryPort reservationRepositoryPort;

    public void validateUserHasReservation(User user) {
        List<Reservation> reservationList = reservationRepositoryPort.findByUserId(user.getId());
        for (Reservation r : reservationList) {
            if (r.getStatus().equals(StatusReservation.ACTIVE)) {
                throw new UserAlreadyHasReservationException();
            }
        }
    }

    public void validateTimeWithinFieldSchedule(Reservation reservation, Field field) {
        LocalTime start = reservation.getStartTime();
        LocalTime end = reservation.getEndTime();

        if (start.isBefore(field.getOpeningHour()) || start.isAfter(field.getClosingHour())) {
            throw new ReservationTimeOutOfRangeException("La hora de inicio está fuera del horario del campo");
        }

        if (end.isAfter(field.getClosingHour())) {
            throw new ReservationTimeOutOfRangeException("La hora de finalizado está fuera del horario del campo");
        }
    }

    public void validateFieldAvailability(Reservation reservation, Field field) {
        List<Reservation> reservations = field.getReservations();
        for (Reservation r: reservations) {
            if (r.getStatus().equals(StatusReservation.ACTIVE) && Objects.equals(r.getField().getId(), field.getId())) {
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
