package com.lgsoftworks.application.reservation.dto.response;

import com.lgsoftworks.application.field.dto.response.FieldDTO;
import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.reservation.enums.ReservationDuration;
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

    private String user;
    private String cellphone;

    // Info del Field
    private Long fieldId;
    private String fieldName;

    // Info del Venue deducido
    private Long venueId;
    private String venueName;

    private ReservationDuration duration;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;

    private Status status;
}
