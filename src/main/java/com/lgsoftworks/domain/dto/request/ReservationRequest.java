package com.lgsoftworks.domain.dto.request;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.domain.model.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest {
    private Long id;
    private Team team;
    private Field field;
    private Byte hours;
    private LocalTime startTime;
    private LocalDate reservationDate;
    private StatusReservation status;
}
