package com.lgsoftworks.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TeamRequest {
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @NotBlank(message = "El barrio es requerido")
    @Size(min = 3, max = 50, message = "El barrio debe tener entre 3 y 50 caracteres")
    private String neighborhood;

    @NotBlank(message = "La ciudad es requerida")
    @Size(min = 3, max = 50, message = "La ciudad debe tener entre 3 y 50 caracteres")
    private String city;

    @NotNull(message = "El máximo de jugadores es requerido")
    @Min(value = 4, message = "El máximo de jugadores debe ser mayor que 3")
    private Integer maxPlayers;

    @NotNull(message = "El ID del dueño es requerido")
    private Long ownerId;

    private String imageUrl;
}
