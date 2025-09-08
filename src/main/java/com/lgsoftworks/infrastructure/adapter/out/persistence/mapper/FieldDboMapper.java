package com.lgsoftworks.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldEntity;

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
        field.setImageUrl(entity.getImageUrl());
        field.setAdmin(UserDboMapper.toModel(entity.getAdmin()));
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
        fieldEntity.setImageUrl(field.getImageUrl());
        fieldEntity.setAdmin(UserDboMapper.toDbo(field.getAdmin()));
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
        field.setAdmin(UserDboMapper.toModel(entity.getAdmin()));
        field.setImageUrl(entity.getImageUrl());

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
        fieldEntity.setAdmin(UserDboMapper.toDbo(field.getAdmin()));
        fieldEntity.setImageUrl(field.getImageUrl());

        return fieldEntity;
    }

}
