package com.lgsoftworks.application.dto.request;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.domain.model.Team;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest {
    private Long id;

    @NotNull(message = "El ID del equipo es requerido")
    private Long teamId;

    @NotNull(message = "El ID de la cancha es requerida")
    private Long fieldId;

    @NotNull(message = "Las horas de reserva son requeridas")
    @Positive(message = "Las horas de reserva deben ser mayores a cero")
    private Byte hours;

    @NotNull(message = "La hora de inicio de reserva es requerida")
    private LocalTime startTime;

    @NotNull(message = "La fecha de reserva es requerida")
    @FutureOrPresent(message = "La fecha de reserva no puede estar en el pasado")
    private LocalDate reservationDate;

    private StatusReservation status;
}
