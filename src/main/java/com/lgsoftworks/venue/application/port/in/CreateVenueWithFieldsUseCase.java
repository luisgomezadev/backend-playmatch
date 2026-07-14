package com.lgsoftworks.venue.application.port.in;

import com.lgsoftworks.venue.application.dto.request.CreateVenueWithFieldsRequest;
import com.lgsoftworks.venue.application.dto.response.VenueWithFieldsDTO;

public interface CreateVenueWithFieldsUseCase {
    VenueWithFieldsDTO execute(CreateVenueWithFieldsRequest request);
}