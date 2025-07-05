package com.lgsoftworks.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.domain.model.FieldAdmin;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldAdminEntity;

public class FieldAdminDboMapper {

    public static FieldAdmin toModel(FieldAdminEntity entity) {
        if (entity == null) return null;
        FieldAdmin fieldAdmin = new FieldAdmin();
        fieldAdmin.setId(entity.getId());
        fieldAdmin.setFirstName(entity.getFirstName());
        fieldAdmin.setLastName(entity.getLastName());
        fieldAdmin.setCity(entity.getCity());
        fieldAdmin.setAge(entity.getAge());
        fieldAdmin.setCellphone(entity.getCellphone());
        fieldAdmin.setDocumentType(entity.getDocumentType());
        fieldAdmin.setDocumentNumber(entity.getDocumentNumber());
        fieldAdmin.setEmail(entity.getEmail());
        fieldAdmin.setPassword(entity.getPassword());
        fieldAdmin.setRole(entity.getRole());
        fieldAdmin.setImageUrl(entity.getImageUrl());
        fieldAdmin.setField(FieldDboMapper.toSimpleModel(entity.getField()));
        return fieldAdmin;
    }

    public static FieldAdminEntity toDbo(FieldAdmin fieldAdmin) {
        if (fieldAdmin == null) return null;
        FieldAdminEntity fieldAdminEntity = new FieldAdminEntity();
        fieldAdminEntity.setId(fieldAdmin.getId());
        fieldAdminEntity.setFirstName(fieldAdmin.getFirstName());
        fieldAdminEntity.setLastName(fieldAdmin.getLastName());
        fieldAdminEntity.setCity(fieldAdmin.getCity());
        fieldAdminEntity.setAge(fieldAdmin.getAge());
        fieldAdminEntity.setCellphone(fieldAdmin.getCellphone());
        fieldAdminEntity.setDocumentType(fieldAdmin.getDocumentType());
        fieldAdminEntity.setDocumentNumber(fieldAdmin.getDocumentNumber());
        fieldAdminEntity.setEmail(fieldAdmin.getEmail());
        fieldAdminEntity.setPassword(fieldAdmin.getPassword());
        fieldAdminEntity.setRole(fieldAdmin.getRole());
        fieldAdminEntity.setImageUrl(fieldAdmin.getImageUrl());
        fieldAdminEntity.setField(FieldDboMapper.toSimpleDbo(fieldAdmin.getField()));
        return fieldAdminEntity;
    }

    public static FieldAdmin toSimpleModel(FieldAdminEntity entity) {
        if (entity == null) return null;
        FieldAdmin fieldAdmin = new FieldAdmin();
        fieldAdmin.setId(entity.getId());
        fieldAdmin.setFirstName(entity.getFirstName());
        fieldAdmin.setLastName(entity.getLastName());
        fieldAdmin.setCity(entity.getCity());
        fieldAdmin.setAge(entity.getAge());
        fieldAdmin.setCellphone(entity.getCellphone());
        fieldAdmin.setDocumentType(entity.getDocumentType());
        fieldAdmin.setDocumentNumber(entity.getDocumentNumber());
        fieldAdmin.setEmail(entity.getEmail());
        fieldAdmin.setPassword(entity.getPassword());
        fieldAdmin.setRole(entity.getRole());
        return fieldAdmin;
    }

    public static FieldAdminEntity toSimpleDbo(FieldAdmin fieldAdmin) {
        if (fieldAdmin == null) return null;
        FieldAdminEntity fieldAdminEntity = new FieldAdminEntity();
        fieldAdminEntity.setId(fieldAdmin.getId());
        fieldAdminEntity.setFirstName(fieldAdmin.getFirstName());
        fieldAdminEntity.setLastName(fieldAdmin.getLastName());
        fieldAdminEntity.setCity(fieldAdmin.getCity());
        fieldAdminEntity.setAge(fieldAdmin.getAge());
        fieldAdminEntity.setCellphone(fieldAdmin.getCellphone());
        fieldAdminEntity.setDocumentType(fieldAdmin.getDocumentType());
        fieldAdminEntity.setDocumentNumber(fieldAdmin.getDocumentNumber());
        fieldAdminEntity.setEmail(fieldAdmin.getEmail());
        fieldAdminEntity.setPassword(fieldAdmin.getPassword());
        fieldAdminEntity.setRole(fieldAdmin.getRole());
        return fieldAdminEntity;
    }

}
