package com.lgsoftworks.application.field.dto.mapper;


import com.lgsoftworks.application.field.dto.request.FieldRequest;
import com.lgsoftworks.application.field.dto.response.FieldDTO;
import com.lgsoftworks.domain.field.enums.FieldType;
import com.lgsoftworks.domain.field.model.Field;

import java.math.BigDecimal;

public class FieldModelMapper {

    public static FieldDTO toDTO(Field field) {
        if (field == null) return null;

        FieldDTO dto = new FieldDTO();
        dto.setId(field.getId());
        dto.setName(field.getName());
        dto.setFieldType(field.getFieldType());
        dto.setHourlyRate(field.getHourlyRate());

        return dto;
    }

    public static Field toModel(FieldDTO dto) {
        if (dto == null) return null;

        return buildField(
                dto.getId(),
                dto.getName(),
                dto.getFieldType(),
                dto.getHourlyRate()
        );
    }

    public static Field toModelRequest(FieldRequest request) {
        if (request == null) return null;

        return buildField(
                request.getId(),
                request.getName(),
                request.getFieldType(),
                request.getHourlyRate()
        );
    }

    private static Field buildField(Long id, String name, FieldType fieldType, BigDecimal hourlyRate) {
        Field field = new Field();
        field.setId(id);
        field.setName(name);
        field.setFieldType(fieldType);
        field.setHourlyRate(hourlyRate);
        return field;
    }

}
