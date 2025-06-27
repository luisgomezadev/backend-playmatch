package com.lgsoftworks.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamApplicationRequest {
    private Long id;

    @NotBlank(message = "La descripción de la solicitud es requerida")
    @Length(min = 3, max = 200, message = "La descripción debe tener entre 3 a 200 caracteres")
    private String description;

    @NotNull(message = "El ID del jugador es requerido")
    private Long playerId;

    @NotNull(message = "El ID del equipo es requerido")
    private Long teamId;
}
