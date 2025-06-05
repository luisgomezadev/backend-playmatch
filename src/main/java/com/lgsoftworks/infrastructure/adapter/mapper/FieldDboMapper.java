package com.lgsoftworks.infrastructure.adapter.mapper;

import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.infrastructure.adapter.entity.FieldEntity;

public class FieldDboMapper {

    public static Field toModel(FieldEntity entity) {
        if (entity == null) return null;
        Field field = new Field();
        field.setId(entity.getId());
        field.setName(entity.getName());
        field.setCity(entity.getCity());
        field.setAddress(entity.getAddress());
        field.setHourlyRate(entity.getHourlyRate());
        field.setOpeningHour(entity.getOpeningHour());
        field.setClosingHour(entity.getClosingHour());
        field.setStatus(entity.getStatus());
        field.setAdmin(AdminDboMapper.toSimpleModel(entity.getAdmin()));
        field.setReservations(ReservationDboMapper.toModelList(entity.getReservations()));

        return field;
    }

    public static FieldEntity toDbo(Field field) {
        if (field == null) return null;
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setId(field.getId());
        fieldEntity.setName(field.getName());
        fieldEntity.setCity(field.getCity());
        fieldEntity.setAddress(field.getAddress());
        fieldEntity.setHourlyRate(field.getHourlyRate());
        fieldEntity.setOpeningHour(field.getOpeningHour());
        fieldEntity.setClosingHour(field.getClosingHour());
        fieldEntity.setStatus(field.getStatus());
        fieldEntity.setAdmin(AdminDboMapper.toSimpleDbo(field.getAdmin()));
        fieldEntity.setReservations(ReservationDboMapper.toDboList(field.getReservations()));

        return fieldEntity;
    }

    public static Field toSimpleModel(FieldEntity entity) {
        if (entity == null) return null;
        Field field = new Field();
        field.setId(entity.getId());
        field.setName(entity.getName());
        field.setCity(entity.getCity());
        field.setAddress(entity.getAddress());
        field.setHourlyRate(entity.getHourlyRate());
        field.setOpeningHour(entity.getOpeningHour());
        field.setClosingHour(entity.getClosingHour());
        field.setStatus(entity.getStatus());

        return field;
    }

    public static FieldEntity toSimpleDbo(Field field) {
        if (field == null) return null;
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setId(field.getId());
        fieldEntity.setName(field.getName());
        fieldEntity.setCity(field.getCity());
        fieldEntity.setAddress(field.getAddress());
        fieldEntity.setHourlyRate(field.getHourlyRate());
        fieldEntity.setOpeningHour(field.getOpeningHour());
        fieldEntity.setClosingHour(field.getClosingHour());
        fieldEntity.setStatus(field.getStatus());

        return fieldEntity;
    }

}
