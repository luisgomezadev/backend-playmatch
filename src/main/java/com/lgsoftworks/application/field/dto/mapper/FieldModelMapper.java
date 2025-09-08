package com.lgsoftworks.application.field.dto.mapper;


import com.lgsoftworks.application.field.dto.request.FieldRequest;
import com.lgsoftworks.application.field.dto.response.FieldDTO;
import com.lgsoftworks.application.user.dto.mapper.UserModelMapper;
import com.lgsoftworks.domain.field.model.Field;

public class FieldModelMapper {

    public static FieldDTO toDTO(Field field) {
        if (field == null) return null;
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setId(field.getId());
        fieldDTO.setName(field.getName());
        fieldDTO.setCity(field.getCity());
        fieldDTO.setAddress(field.getAddress());
        fieldDTO.setHourlyRate(field.getHourlyRate());
        fieldDTO.setOpeningHour(field.getOpeningHour());
        fieldDTO.setClosingHour(field.getClosingHour());
        fieldDTO.setStatus(field.getStatus());
        fieldDTO.setImageUrl(field.getImageUrl());
        fieldDTO.setAdmin(UserModelMapper.toUserDTO(field.getAdmin()));

        return fieldDTO;
    }

    public static Field toModel(FieldDTO fieldDTO) {
        if (fieldDTO == null) return null;
        Field field = new Field();
        field.setId(fieldDTO.getId());
        field.setName(fieldDTO.getName());
        field.setCity(fieldDTO.getCity());
        field.setAddress(fieldDTO.getAddress());
        field.setHourlyRate(fieldDTO.getHourlyRate());
        field.setOpeningHour(fieldDTO.getOpeningHour());
        field.setClosingHour(fieldDTO.getClosingHour());
        field.setStatus(fieldDTO.getStatus());
        field.setImageUrl(fieldDTO.getImageUrl());
        field.setAdmin(UserModelMapper.toUser(fieldDTO.getAdmin()));

        return field;
    }

    public static Field toModelRequest(FieldRequest fieldRequest) {
        if (fieldRequest == null) return null;
        Field field = new Field();
        field.setId(fieldRequest.getId());
        field.setName(fieldRequest.getName());
        field.setCity(fieldRequest.getCity());
        field.setAddress(fieldRequest.getAddress());
        field.setHourlyRate(fieldRequest.getHourlyRate());
        field.setOpeningHour(fieldRequest.getOpeningHour());
        field.setClosingHour(fieldRequest.getClosingHour());
        field.setStatus(fieldRequest.getStatus());
        field.setImageUrl(fieldRequest.getImageUrl());

        return field;
    }

}
