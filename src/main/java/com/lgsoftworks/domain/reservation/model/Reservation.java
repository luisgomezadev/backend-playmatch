package com.lgsoftworks.domain.reservation.model;

import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.user.model.User;
import com.lgsoftworks.domain.reservation.enums.StatusReservation;
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
    private User user;
    private Field field;
    private Byte hours;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;
    private StatusReservation status;

}
