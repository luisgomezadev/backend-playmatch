package com.lgsoftworks.venue.application.dto.response;

import com.lgsoftworks.field.application.dto.response.FieldDTO;

import java.util.List;

public record VenueWithFieldsDTO(VenueDTO venue, List<FieldDTO> fields) {
}