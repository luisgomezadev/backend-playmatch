package com.lgsoftworks.reservation.domain.model;

import com.lgsoftworks.reservation.domain.exception.InvalidReservationDataException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@EqualsAndHashCode(of = "id")
public class Reservation {
    private Long id;
    private String code;
    private String customerName;
    private String cellphone;
    private Long fieldId;
    private ReservationDuration duration;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;
    private ReservationStatus status;

    protected Reservation() {
    }

    public static Reservation create(String code, String customerName, String cellphone,
                                     Long fieldId, LocalDate reservationDate,
                                     LocalTime startTime, ReservationDuration duration) {
        Reservation reservation = new Reservation();
        reservation.code = requireNonBlank(code, "El código de reserva es requerido");
        reservation.customerName = requireNonBlank(customerName, "El nombre del cliente es requerido");
        reservation.cellphone = requireNonBlank(cellphone, "El celular del cliente es requerido");
        reservation.fieldId = Objects.requireNonNull(fieldId, "La cancha es requerida");
        reservation.reservationDate = Objects.requireNonNull(reservationDate, "La fecha es requerida");
        reservation.startTime = Objects.requireNonNull(startTime, "La hora de inicio es requerida");
        reservation.duration = Objects.requireNonNull(duration, "La duración es requerida");
        reservation.endTime = startTime.plusMinutes(duration.getMinutes());
        reservation.status = ReservationStatus.ACTIVE;
        return reservation;
    }

    public static Reservation rehydrate(Long id, String code, String customerName, String cellphone,
                                        Long fieldId, ReservationDuration duration,
                                        LocalTime startTime, LocalTime endTime,
                                        LocalDate reservationDate, ReservationStatus status) {
        Reservation reservation = new Reservation();
        reservation.id = id;
        reservation.code = code;
        reservation.customerName = customerName;
        reservation.cellphone = cellphone;
        reservation.fieldId = fieldId;
        reservation.duration = duration;
        reservation.startTime = startTime;
        reservation.endTime = endTime;
        reservation.reservationDate = reservationDate;
        reservation.status = status;
        return reservation;
    }

    public void cancel() {
        if (this.status != ReservationStatus.ACTIVE) {
            throw new IllegalStateException("Solo se puede cancelar una reserva activa");
        }
        this.status = ReservationStatus.CANCELED;
    }

    public boolean overlapsWith(Reservation other) {
        return this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime);
    }

    private static String requireNonBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new InvalidReservationDataException(message);
        }
        return value;
    }

}