package com.lgsoftworks.application.dto.request;

import com.lgsoftworks.domain.enums.StatusReservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationFilter {

    private LocalDate date;
    private StatusReservation status;
    private Long userId;
    private Long fieldId;
}
