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
        admin.setCellphone(entity.getCellphone());
        admin.setDocumentType(entity.getDocumentType());
        admin.setDocumentNumber(entity.getDocumentNumber());
        admin.setEmail(entity.getEmail());
        admin.setPassword(entity.getPassword());
        admin.setRole(entity.getRole());
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
        adminEntity.setCellphone(admin.getCellphone());
        adminEntity.setDocumentType(admin.getDocumentType());
        adminEntity.setDocumentNumber(admin.getDocumentNumber());
        adminEntity.setEmail(admin.getEmail());
        adminEntity.setPassword(admin.getPassword());
        adminEntity.setRole(admin.getRole());
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
        admin.setCellphone(entity.getCellphone());
        admin.setDocumentType(entity.getDocumentType());
        admin.setDocumentNumber(entity.getDocumentNumber());
        admin.setEmail(entity.getEmail());
        admin.setPassword(entity.getPassword());
        admin.setRole(entity.getRole());
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
        adminEntity.setCellphone(admin.getCellphone());
        adminEntity.setDocumentType(admin.getDocumentType());
        adminEntity.setDocumentNumber(admin.getDocumentNumber());
        adminEntity.setEmail(admin.getEmail());
        adminEntity.setPassword(admin.getPassword());
        adminEntity.setRole(admin.getRole());
        return adminEntity;
    }

}
