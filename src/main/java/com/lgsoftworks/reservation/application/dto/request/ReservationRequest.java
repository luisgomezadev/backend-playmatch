package com.lgsoftworks.reservation.application.dto.request;

import com.lgsoftworks.reservation.domain.model.ReservationDuration;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "El nombre del usuario es requerido")
    private String customerName;

    @NotNull(message = "El número de teléfono es requerido")
    @Size(min = 10, max = 10, message = "El número de teléfono debe tener 10 dígitos")
    private String cellphone;

    @NotNull(message = "El ID de la cancha es requerido")
    private Long fieldId;

    @NotNull(message = "La duración de la reserva es requerida")
    private ReservationDuration duration;

    @NotNull(message = "La hora de inicio de reserva es requerida")
    private LocalTime startTime;

    @NotNull(message = "La fecha de reserva es requerida")
    @FutureOrPresent(message = "La fecha de reserva no puede estar en el pasado")
    private LocalDate reservationDate;
}
