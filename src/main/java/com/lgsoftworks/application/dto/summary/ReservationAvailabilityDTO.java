package com.lgsoftworks.application.dto.summary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class ReservationAvailabilityDTO {
    private Byte hours;
    private TeamSummaryDTO team;
    private FieldSummaryDTO field;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;
}
