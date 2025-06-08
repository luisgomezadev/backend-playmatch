package com.lgsoftworks.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TeamRequest {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotBlank(message = "El barrio no puede estar vacío")
    private String neighborhood;
    @NotBlank(message = "La ciudad no puede estar vacía")
    private String city;
    @Min(value = 4, message = "El máximo de jugadores debe ser mayor que 3")
    private Integer maxPlayers;
    private Long ownerId;
}
