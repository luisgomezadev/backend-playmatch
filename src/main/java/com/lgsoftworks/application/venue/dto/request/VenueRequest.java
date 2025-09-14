package com.lgsoftworks.application.venue.dto.request;

import com.lgsoftworks.application.field.dto.request.FieldRequest;
import com.lgsoftworks.domain.common.enums.Status;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VenueRequest {

    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name;

    @NotBlank(message = "El código es requerido")
    @Size(min = 3, max = 50, message = "El código debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "El código solo puede contener letras, números o guiones, sin espacios ni acentos")
    private String code;

    @NotBlank(message = "La ciudad es requerida")
    @Size(min = 3, max = 50, message = "La ciudad debe tener entre 3 y 50 caracteres")
    private String city;

    @NotBlank(message = "La dirección es requerida")
    @Size(min = 5, max = 150, message = "La dirección debe tener entre 5 y 150 caracteres")
    private String address;

    @NotNull(message = "La hora de inicio es requerida")
    private LocalTime openingHour;

    @NotNull(message = "La hora de cierre es requerida")
    private LocalTime closingHour;

    @NotNull(message = "El ID del admin es requerido")
    private Long adminId;

    private Status status;

    @NotNull(message = "Debe enviar al menos una cancha")
    @Size(min = 1, message = "Debe enviar al menos una cancha")
    private List<FieldRequest> fields = new ArrayList<>();

}
