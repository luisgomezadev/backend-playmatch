package com.lgsoftworks.infrastructure.rest.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TeamRequest {
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotBlank(message = "El barrio es requerido")
    private String neighborhood;

    @NotBlank(message = "La ciudad es requerida")
    private String city;

    @NotNull(message = "El máximo de jugadores es requerido")
    @Min(value = 4, message = "El máximo de jugadores debe ser mayor que 3")
    private Integer maxPlayers;

    @NotNull(message = "El ID del dueño es requerido")
    private Long ownerId;
}
