package com.lgsoftworks.application.mapper;

import com.lgsoftworks.application.dto.FieldDTO;
import com.lgsoftworks.application.dto.request.FieldRequest;
import com.lgsoftworks.application.dto.summary.FieldSummaryDTO;
import com.lgsoftworks.domain.model.Field;

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
        fieldDTO.setReservations(ReservationModelMapper.toReservationFieldDTOList(field.getReservations()));

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
        field.setReservations(ReservationModelMapper.toReservationFieldList(fieldDTO.getReservations()));

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

    public static FieldSummaryDTO toFieldSummaryDTO(Field field) {
        if (field == null) return null;
        FieldSummaryDTO fieldSummaryDTO = new FieldSummaryDTO();
        fieldSummaryDTO.setId(field.getId());
        fieldSummaryDTO.setName(field.getName());
        fieldSummaryDTO.setCity(field.getCity());
        fieldSummaryDTO.setAddress(field.getAddress());
        fieldSummaryDTO.setHourlyRate(field.getHourlyRate());
        fieldSummaryDTO.setOpeningHour(field.getOpeningHour());
        fieldSummaryDTO.setClosingHour(field.getClosingHour());
        fieldSummaryDTO.setStatus(field.getStatus());
        fieldSummaryDTO.setAdmin(UserModelMapper.toUserDTO(field.getAdmin()));
        fieldSummaryDTO.setImageUrl(field.getImageUrl());
        return fieldSummaryDTO;
    }

    public static Field dtoSummaryToField(FieldSummaryDTO fieldSummaryDTO) {
        if (fieldSummaryDTO == null) return null;
        Field field = new Field();
        field.setId(fieldSummaryDTO.getId());
        field.setName(fieldSummaryDTO.getName());
        field.setCity(fieldSummaryDTO.getCity());
        field.setAddress(fieldSummaryDTO.getAddress());
        field.setHourlyRate(fieldSummaryDTO.getHourlyRate());
        field.setOpeningHour(fieldSummaryDTO.getOpeningHour());
        field.setClosingHour(fieldSummaryDTO.getClosingHour());
        field.setStatus(fieldSummaryDTO.getStatus());
        field.setAdmin(UserModelMapper.toUser(fieldSummaryDTO.getAdmin()));
        field.setImageUrl(fieldSummaryDTO.getImageUrl());
        return field;
    }

}
