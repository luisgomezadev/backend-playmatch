package com.lgsoftworks.application.mapper;

import com.lgsoftworks.infrastructure.rest.dto.AdminDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.AdminRequest;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.model.Admin;

public class AdminModelMapper {

    public static AdminDTO toDTO(Admin admin) {
        if (admin == null) return null;
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setLastName(admin.getLastName());
        adminDTO.setCity(admin.getCity());
        adminDTO.setAge(admin.getAge());
        adminDTO.setDocumentType(admin.getDocumentType());
        adminDTO.setDocumentNumber(admin.getDocumentNumber());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setField(FieldModelMapper.toFieldSummaryDTO(admin.getField()));
        return adminDTO;
    }

    public static Admin toModel(AdminDTO adminDTO) {
        if (adminDTO == null) return null;
        Admin admin = new Admin();
        admin.setId(adminDTO.getId());
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setCity(adminDTO.getCity());
        admin.setAge(adminDTO.getAge());
        admin.setDocumentType(adminDTO.getDocumentType());
        admin.setDocumentNumber(adminDTO.getDocumentNumber());
        admin.setEmail(adminDTO.getEmail());
        admin.setField(FieldModelMapper.dtoSummaryToField(adminDTO.getField()));
        return admin;
    }

    public static Admin toModelRequest(AdminRequest adminRequest) {
        if (adminRequest == null) return null;
        Admin admin = new Admin();
        admin.setId(adminRequest.getId());
        admin.setFirstName(adminRequest.getFirstName());
        admin.setLastName(adminRequest.getLastName());
        admin.setCity(adminRequest.getCity());
        admin.setAge(adminRequest.getAge());
        admin.setDocumentType(adminRequest.getDocumentType());
        admin.setDocumentNumber(adminRequest.getDocumentNumber());
        admin.setEmail(adminRequest.getEmail());
        admin.setField(adminRequest.getField());
        admin.setPassword(adminRequest.getPassword());
        admin.setRole(Role.ADMIN);
        return admin;
    }

}
