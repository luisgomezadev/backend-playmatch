package com.lgsoftworks.venue.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CreateVenueWithFieldsRequest {

    @Valid
    @NotNull(message = "Los datos del venue son requeridos")
    private VenueRequest venue;

    @NotEmpty(message = "Debe enviar al menos una cancha")
    @Valid
    private List<CreateFieldItemRequest> fields;
}