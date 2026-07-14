package com.lgsoftworks.reservation.application.dto.request;

import com.lgsoftworks.reservation.domain.model.ReservationStatus;
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
    private ReservationStatus status;
    private Long userId;
    private Long fieldId;
}
