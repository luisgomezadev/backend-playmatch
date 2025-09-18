package com.lgsoftworks.application.field.dto.request;

import com.lgsoftworks.domain.field.enums.FieldType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre de la cancha es requerido")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @NotNull(message = "El tipo de cancha es requerido")
    private FieldType fieldType;

    @NotNull(message = "El precio por hora es requerido")
    @Positive(message = "El precio debe ser positivo")
    private BigDecimal hourlyRate;
}
