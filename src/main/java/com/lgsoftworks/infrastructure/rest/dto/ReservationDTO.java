package com.lgsoftworks.infrastructure.rest.dto;

import com.lgsoftworks.infrastructure.rest.dto.summary.FieldSummaryDTO;
import com.lgsoftworks.infrastructure.rest.dto.summary.TeamSummaryDTO;
import com.lgsoftworks.domain.enums.StatusReservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class ReservationDTO {
    private Long id;
    private TeamSummaryDTO team;
    private FieldSummaryDTO field;
    private Byte hours;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;
    private StatusReservation status;
}
