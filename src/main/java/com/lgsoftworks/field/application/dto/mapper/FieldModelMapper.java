package com.lgsoftworks.field.application.dto.mapper;

import com.lgsoftworks.field.application.dto.response.FieldDTO;
import com.lgsoftworks.field.domain.model.Field;

public class FieldModelMapper {

    private FieldModelMapper(){}

    public static FieldDTO toDTO(Field field) {
        if (field == null) return null;
        FieldDTO dto = new FieldDTO();
        dto.setId(field.getId());
        dto.setName(field.getName());
        dto.setFieldType(field.getFieldType());
        dto.setHourlyRate(field.getHourlyRate());
        dto.setVenueId(field.getVenueId().value());
        return dto;
    }

}