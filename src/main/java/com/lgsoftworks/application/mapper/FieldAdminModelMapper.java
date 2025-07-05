package com.lgsoftworks.application.mapper;

import com.lgsoftworks.application.dto.FieldAdminDTO;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.model.FieldAdmin;

public class FieldAdminModelMapper {

    public static FieldAdminDTO toDTO(FieldAdmin fieldAdmin) {
        if (fieldAdmin == null) return null;
        FieldAdminDTO fieldAdminDTO = new FieldAdminDTO();
        fieldAdminDTO.setId(fieldAdmin.getId());
        fieldAdminDTO.setFirstName(fieldAdmin.getFirstName());
        fieldAdminDTO.setLastName(fieldAdmin.getLastName());
        fieldAdminDTO.setCity(fieldAdmin.getCity());
        fieldAdminDTO.setAge(fieldAdmin.getAge());
        fieldAdminDTO.setCellphone(fieldAdmin.getCellphone());
        fieldAdminDTO.setDocumentType(fieldAdmin.getDocumentType());
        fieldAdminDTO.setDocumentNumber(fieldAdmin.getDocumentNumber());
        fieldAdminDTO.setEmail(fieldAdmin.getEmail());
        fieldAdminDTO.setImageUrl(fieldAdmin.getImageUrl());
        fieldAdminDTO.setField(FieldModelMapper.toFieldSummaryDTO(fieldAdmin.getField()));
        return fieldAdminDTO;
    }

    public static FieldAdmin toModel(FieldAdminDTO fieldAdminDTO) {
        if (fieldAdminDTO == null) return null;
        FieldAdmin fieldAdmin = new FieldAdmin();
        fieldAdmin.setId(fieldAdminDTO.getId());
        fieldAdmin.setFirstName(fieldAdminDTO.getFirstName());
        fieldAdmin.setLastName(fieldAdminDTO.getLastName());
        fieldAdmin.setCity(fieldAdminDTO.getCity());
        fieldAdmin.setAge(fieldAdminDTO.getAge());
        fieldAdmin.setCellphone(fieldAdminDTO.getCellphone());
        fieldAdmin.setDocumentType(fieldAdminDTO.getDocumentType());
        fieldAdmin.setDocumentNumber(fieldAdminDTO.getDocumentNumber());
        fieldAdmin.setEmail(fieldAdminDTO.getEmail());
        fieldAdmin.setImageUrl(fieldAdminDTO.getImageUrl());
        fieldAdmin.setField(FieldModelMapper.dtoSummaryToField(fieldAdminDTO.getField()));
        return fieldAdmin;
    }

    public static FieldAdmin toModelRequest(UserRequest fieldAdminRequest) {
        if (fieldAdminRequest == null) return null;
        FieldAdmin fieldAdmin = new FieldAdmin();
        fieldAdmin.setId(fieldAdminRequest.getId());
        fieldAdmin.setFirstName(fieldAdminRequest.getFirstName());
        fieldAdmin.setLastName(fieldAdminRequest.getLastName());
        fieldAdmin.setCity(fieldAdminRequest.getCity());
        fieldAdmin.setAge(fieldAdminRequest.getAge());
        fieldAdmin.setCellphone(fieldAdminRequest.getCellphone());
        fieldAdmin.setDocumentType(fieldAdminRequest.getDocumentType());
        fieldAdmin.setDocumentNumber(fieldAdminRequest.getDocumentNumber());
        fieldAdmin.setEmail(fieldAdminRequest.getEmail());
        fieldAdmin.setPassword(fieldAdminRequest.getPassword());
        fieldAdmin.setRole(Role.FIELD_ADMIN);
        return fieldAdmin;
    }

}
