package com.lgsoftworks.application.dto;

import com.lgsoftworks.application.dto.summary.FieldSummaryDTO;
import com.lgsoftworks.application.dto.summary.TeamSummaryDTO;
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
