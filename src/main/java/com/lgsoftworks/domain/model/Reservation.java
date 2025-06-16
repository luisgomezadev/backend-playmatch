package com.lgsoftworks.domain.model;

import com.lgsoftworks.domain.enums.StatusReservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private Long id;
    private Team team;
    private Field field;
    private Byte hours;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;
    private StatusReservation status;

    public LocalDateTime getEndDateTime() {
        return LocalDateTime.of(reservationDate, endTime);
    }

    public void finalizeIfExpired(LocalDateTime now) {
        if (status == StatusReservation.ACTIVE && now.isAfter(getEndDateTime())) {
            this.status = StatusReservation.FINISHED;
        }
    }
}
