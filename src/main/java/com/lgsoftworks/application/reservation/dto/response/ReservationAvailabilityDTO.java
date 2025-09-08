package com.lgsoftworks.application.reservation.dto.response;

import com.lgsoftworks.application.user.dto.response.UserDTO;
import com.lgsoftworks.application.field.dto.response.FieldDTO;
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
    private FieldDTO field;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;
}
