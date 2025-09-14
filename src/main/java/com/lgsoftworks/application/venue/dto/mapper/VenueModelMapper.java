package com.lgsoftworks.application.venue.dto.mapper;

import com.lgsoftworks.application.field.dto.mapper.FieldModelMapper;
import com.lgsoftworks.application.field.dto.response.FieldDTO;
import com.lgsoftworks.application.user.dto.mapper.UserModelMapper;
import com.lgsoftworks.application.venue.dto.request.VenueRequest;
import com.lgsoftworks.application.venue.dto.response.VenueDTO;
import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.venue.model.Venue;

import java.util.List;
import java.util.stream.Collectors;

public class VenueModelMapper {

        public static VenueDTO toDTO(Venue venue) {
            if (venue == null) return null;

            VenueDTO dto = new VenueDTO();
            dto.setId(venue.getId());
            dto.setCode(venue.getCode());
            dto.setName(venue.getName());
            dto.setCity(venue.getCity());
            dto.setAddress(venue.getAddress());
            dto.setAdmin(UserModelMapper.toUserDTO(venue.getAdmin()));
            dto.setOpeningHour(venue.getOpeningHour());
            dto.setClosingHour(venue.getClosingHour());
            dto.setStatus(venue.getStatus());

            if (venue.getFields() != null && !venue.getFields().isEmpty()) {
                List<FieldDTO> fields = venue.getFields().stream()
                        .map(FieldModelMapper::toDTO)
                        .collect(Collectors.toList());
                dto.setFields(fields);
            }

            return dto;
        }

        public static Venue toModel(VenueDTO dto) {
            if (dto == null) return null;

            Venue venue = new Venue();
            venue.setId(dto.getId());
            venue.setCode(dto.getCode());
            venue.setName(dto.getName());
            venue.setCity(dto.getCity());
            venue.setAddress(dto.getAddress());
            venue.setAdmin(UserModelMapper.toUser(dto.getAdmin()));
            venue.setOpeningHour(dto.getOpeningHour());
            venue.setClosingHour(dto.getClosingHour());
            venue.setStatus(dto.getStatus());

            // No seteamos las canchas aquí; se asignan en el servicio o mapper de request
            return venue;
        }

    public static Venue toModelRequest(VenueRequest request) {
        if (request == null) return null;

        Venue venue = new Venue();
        // 🔹 ahora se incluye el ID del venue para updates
        venue.setId(request.getId());
        venue.setName(request.getName());
        venue.setCode(request.getCode());
        venue.setCity(request.getCity());
        venue.setAddress(request.getAddress());
        venue.setOpeningHour(request.getOpeningHour());
        venue.setClosingHour(request.getClosingHour());
        venue.setStatus(request.getStatus());

        // 🔹 Mapear las canchas (con ID para update)
        if (request.getFields() != null && !request.getFields().isEmpty()) {
            List<Field> fields = request.getFields().stream()
                    .map(f -> {
                        Field field = new Field();
                        // incluir ID del field si viene
                        field.setId(f.getId());
                        field.setName(f.getName());
                        field.setFieldType(f.getFieldType());
                        field.setHourlyRate(f.getHourlyRate());
                        field.setVenue(venue); // asociar el venue
                        return field;
                    })
                    .collect(Collectors.toList());
            venue.setFields(fields);
        }

        return venue;
    }

}