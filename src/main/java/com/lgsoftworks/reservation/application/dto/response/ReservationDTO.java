package com.lgsoftworks.reservation.application.dto.response;

import com.lgsoftworks.reservation.domain.model.ReservationDuration;
import com.lgsoftworks.reservation.domain.model.ReservationStatus;
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
    private String code;
    private String customerName;
    private String cellphone;
    private Long fieldId;
    private ReservationDuration duration;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;
    private ReservationStatus status;
}