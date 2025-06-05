package com.lgsoftworks.infrastructure.adapter.entity;

import com.lgsoftworks.domain.enums.StatusReservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservation")
@NoArgsConstructor
@Getter
@Setter
public class ReservationEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;
    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private FieldEntity field;
    @Min(value = 1, message = "Las horas de reserva debe ser al menos 1")
    @Max(value = 3, message = "Las horas de reserva no puede ser mayor a 3")
    private Byte hours;
    private LocalTime startTime;
    private LocalTime endTime;
    @FutureOrPresent(message = "La fecha de reserva debe ser hoy o los siguientes dias")
    private LocalDate reservationDate;
    @Enumerated(EnumType.STRING)
    private StatusReservation status;
}
