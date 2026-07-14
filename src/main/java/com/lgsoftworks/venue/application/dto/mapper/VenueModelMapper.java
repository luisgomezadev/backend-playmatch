package com.lgsoftworks.venue.application.dto.mapper;

import com.lgsoftworks.venue.application.dto.response.VenueDTO;
import com.lgsoftworks.venue.domain.model.Venue;

public class VenueModelMapper {

    private VenueModelMapper() {
    }

    public static VenueDTO toDTO(Venue venue) {
        if (venue == null) return null;
        VenueDTO dto = new VenueDTO();
        dto.setId(venue.getId());
        dto.setCode(venue.getCode());
        dto.setName(venue.getName());
        dto.setCity(venue.getCity());
        dto.setAddress(venue.getAddress());
        dto.setOpeningHour(venue.getOpeningHour());
        dto.setClosingHour(venue.getClosingHour());
        dto.setAdminId(venue.getAdminId());
        dto.setStatus(venue.getStatus());
        return dto;
    }
}