package com.lgsoftworks.application.dto.request;

import com.lgsoftworks.domain.enums.Status;
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

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name;

    @NotBlank(message = "La ciudad es requerida")
    @Size(min = 3, max = 50, message = "La ciudad debe tener entre 3 y 50 caracteres")
    private String city;

    @NotBlank(message = "La dirección es requerida")
    @Size(min = 5, max = 150, message = "La dirección debe tener entre 5 y 150 caracteres")
    private String address;

    @NotNull(message = "El precio por hora es requerido")
    @Positive(message = "El precio por hora debe ser un valor positivo")
    private BigDecimal hourlyRate;

    @NotNull(message = "La hora de inicio es requerida")
    private LocalTime openingHour;

    @NotNull(message = "La hora de cierre es requerida")
    private LocalTime closingHour;

    @NotNull(message = "El ID del admin es requerido")
    private Long adminId;

    private Status status;

    private String imageUrl;
}
