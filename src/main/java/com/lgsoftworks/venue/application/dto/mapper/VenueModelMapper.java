package com.lgsoftworks.venue.application.dto.mapper;

import com.lgsoftworks.venue.application.dto.response.VenueDTO;
import com.lgsoftworks.venue.application.dto.response.VenuePublicDTO;
import com.lgsoftworks.venue.domain.model.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VenueModelMapper {

    VenueDTO toDTO(Venue venue);

    VenuePublicDTO toPublicDTO(Venue venue);
}