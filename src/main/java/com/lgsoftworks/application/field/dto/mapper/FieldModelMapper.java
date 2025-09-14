package com.lgsoftworks.application.field.dto.mapper;


import com.lgsoftworks.application.field.dto.request.FieldRequest;
import com.lgsoftworks.application.field.dto.response.FieldDTO;
import com.lgsoftworks.application.user.dto.mapper.UserModelMapper;
import com.lgsoftworks.domain.field.model.Field;

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

        Field field = new Field();
        field.setId(dto.getId());
        field.setName(dto.getName());
        field.setFieldType(dto.getFieldType());
        field.setHourlyRate(dto.getHourlyRate());

        return field;
    }

    public static Field toModelRequest(FieldRequest request) {
        if (request == null) return null;

        Field field = new Field();
        field.setName(request.getName());
        field.setFieldType(request.getFieldType());
        field.setHourlyRate(request.getHourlyRate());

        return field;
    }

}
