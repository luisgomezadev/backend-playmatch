package com.lgsoftworks.infrastructure.rest.dto.summary;

import com.lgsoftworks.domain.enums.StatusReservation;
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
