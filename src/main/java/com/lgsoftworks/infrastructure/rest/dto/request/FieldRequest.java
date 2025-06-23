package com.lgsoftworks.infrastructure.rest.dto.request;

import com.lgsoftworks.domain.enums.Status;
import com.lgsoftworks.domain.model.Admin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FieldRequest {
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotBlank(message = "La ciudad es requerida")
    private String city;

    @NotBlank(message = "La dirección es requerida")
    private String address;

    @NotNull(message = "El precio por hora es requerido")
    private BigDecimal hourlyRate;

    @NotNull(message = "La hora de inicio es requerida")
    private LocalTime openingHour;

    @NotNull(message = "La hora de cierre es requerida")
    private LocalTime closingHour;

    @NotNull(message = "El ID del admin es requerida")
    private Long adminId;
    private Status status;
}
