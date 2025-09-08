package com.lgsoftworks.application.reservation.dto.response;

import com.lgsoftworks.application.user.dto.response.UserDTO;
import com.lgsoftworks.application.field.dto.response.FieldDTO;
import com.lgsoftworks.domain.reservation.enums.StatusReservation;
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
    private UserDTO user;
    private FieldDTO field;
    private Byte hours;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;
    private StatusReservation status;
}
