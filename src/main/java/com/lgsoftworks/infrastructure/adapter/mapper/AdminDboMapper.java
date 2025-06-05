package com.lgsoftworks.infrastructure.adapter.mapper;

import com.lgsoftworks.domain.model.Admin;
import com.lgsoftworks.infrastructure.adapter.entity.AdminEntity;

public class AdminDboMapper {

    public static Admin toModel(AdminEntity entity) {
        if (entity == null) return null;
        Admin admin = new Admin();
        admin.setId(entity.getId());
        admin.setFirstName(entity.getFirstName());
        admin.setLastName(entity.getLastName());
        admin.setCity(entity.getCity());
        admin.setAge(entity.getAge());
        admin.setDocumentType(entity.getDocumentType());
        admin.setDocumentNumber(entity.getDocumentNumber());
        admin.setEmail(entity.getEmail());
        admin.setField(FieldDboMapper.toSimpleModel(entity.getField()));
        return admin;
    }

    public static AdminEntity toDbo(Admin admin) {
        if (admin == null) return null;
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setId(admin.getId());
        adminEntity.setFirstName(admin.getFirstName());
        adminEntity.setLastName(admin.getLastName());
        adminEntity.setCity(admin.getCity());
        adminEntity.setAge(admin.getAge());
        adminEntity.setDocumentType(admin.getDocumentType());
        adminEntity.setDocumentNumber(admin.getDocumentNumber());
        adminEntity.setEmail(admin.getEmail());
        adminEntity.setField(FieldDboMapper.toSimpleDbo(admin.getField()));
        return adminEntity;
    }

    public static Admin toSimpleModel(AdminEntity entity) {
        if (entity == null) return null;
        Admin admin = new Admin();
        admin.setId(entity.getId());
        admin.setFirstName(entity.getFirstName());
        admin.setLastName(entity.getLastName());
        admin.setCity(entity.getCity());
        admin.setAge(entity.getAge());
        admin.setDocumentType(entity.getDocumentType());
        admin.setDocumentNumber(entity.getDocumentNumber());
        admin.setEmail(entity.getEmail());
        return admin;
    }

    public static AdminEntity toSimpleDbo(Admin admin) {
        if (admin == null) return null;
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setId(admin.getId());
        adminEntity.setFirstName(admin.getFirstName());
        adminEntity.setLastName(admin.getLastName());
        adminEntity.setCity(admin.getCity());
        adminEntity.setAge(admin.getAge());
        adminEntity.setDocumentType(admin.getDocumentType());
        adminEntity.setDocumentNumber(admin.getDocumentNumber());
        adminEntity.setEmail(admin.getEmail());
        return adminEntity;
    }

}
