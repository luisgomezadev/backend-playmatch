package com.lgsoftworks.domain.reservation.model;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.reservation.enums.ReservationDuration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private Long id;
    private String code;
    private String user;
    private String cellphone;
    private Field field;
    private ReservationDuration duration;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;
    private Status status;
}
