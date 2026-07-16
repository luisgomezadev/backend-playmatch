package com.lgsoftworks.reservation.application.dto.mapper;

import com.lgsoftworks.reservation.application.dto.response.ReservationDTO;
import com.lgsoftworks.reservation.domain.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ReservationModelMapper {

    @Mapping(target = "fieldName", ignore = true)
    ReservationDTO toDTO(Reservation reservation);
}