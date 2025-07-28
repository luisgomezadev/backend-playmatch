package com.lgsoftworks.application.dto;

import com.lgsoftworks.application.dto.summary.FieldSummaryDTO;
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
    private UserDTO user;
    private FieldSummaryDTO field;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;
}
