package com.lgsoftworks.domain.validator;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.exception.FieldNotAvailableException;
import com.lgsoftworks.domain.exception.ReservationTimeOutOfRangeException;
import com.lgsoftworks.domain.exception.TeamAlreadyHasReservationException;
import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.domain.model.Reservation;
import com.lgsoftworks.domain.model.Team;

import java.time.LocalTime;
import java.util.List;

public class ReservationValidator {

    public static void validateTeamHasReservation(Team team) {
        for (Reservation r : team.getReservations()) {
            if (r.getStatus().equals(StatusReservation.ACTIVE)) {
                throw new TeamAlreadyHasReservationException(team);
            }
        }
    }

    public static void validateTimeWithinFieldSchedule(Reservation reservation, Field field) {
        LocalTime start = reservation.getStartTime();
        LocalTime end = reservation.getEndTime();

        if (start.isBefore(field.getOpeningHour()) || start.isAfter(field.getClosingHour())) {
            throw new ReservationTimeOutOfRangeException("La hora de inicio está fuera del horario del campo");
        }

        if (end.isAfter(field.getClosingHour())) {
            throw new ReservationTimeOutOfRangeException("La hora de fin está fuera del horario del campo");
        }
    }

    public static void validateFieldAvailability(Reservation reservation, Field field) {
        List<Reservation> reservations = field.getReservations();
        for (Reservation r: reservations) {
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
