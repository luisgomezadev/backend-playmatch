package com.lgsoftworks.application.dto.summary;

import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.domain.enums.StatusReservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class ReservationFieldDTO {
    private Long id;
    private Byte hours;
    private UserDTO user;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate reservationDate;
    private StatusReservation status;
}
