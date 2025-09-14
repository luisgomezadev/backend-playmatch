package com.lgsoftworks.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldEntity;

import java.util.List;
import java.util.stream.Collectors;

public class FieldDboMapper {

    public static Field toModel(FieldEntity entity) {
        if (entity == null) return null;

        Field field = new Field();
        field.setId(entity.getId());
        field.setName(entity.getName());
        field.setFieldType(entity.getFieldType());
        field.setHourlyRate(entity.getHourlyRate());
        field.setVenue(VenueDboMapper.toSimpleModel(entity.getVenue()));
        field.setReservations(ReservationDboMapper.toModelList(entity.getReservations()));

        return field;
    }

    public static FieldEntity toDbo(Field field) {
        if (field == null) return null;

        FieldEntity entity = new FieldEntity();
        entity.setId(field.getId());
        entity.setName(field.getName());
        entity.setFieldType(field.getFieldType());
        entity.setHourlyRate(field.getHourlyRate());
        entity.setVenue(VenueDboMapper.toSimpleDbo(field.getVenue()));
        entity.setReservations(ReservationDboMapper.toDboList(field.getReservations()));

        return entity;
    }

    public static Field toSimpleModel(FieldEntity entity) {
        if (entity == null) return null;

        Field field = new Field();
        field.setId(entity.getId());
        field.setName(entity.getName());
        field.setFieldType(entity.getFieldType());
        field.setHourlyRate(entity.getHourlyRate());
        // Evitar cargar reservas para simplificar
        field.setVenue(VenueDboMapper.toSimpleModel(entity.getVenue()));

        return field;
    }

    public static FieldEntity toSimpleDbo(Field field) {
        if (field == null) return null;

        FieldEntity entity = new FieldEntity();
        entity.setId(field.getId());
        entity.setName(field.getName());
        entity.setFieldType(field.getFieldType());
        entity.setHourlyRate(field.getHourlyRate());
        entity.setVenue(VenueDboMapper.toSimpleDbo(field.getVenue()));

        return entity;
    }

    public static List<Field> toModelList(List<FieldEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(FieldDboMapper::toModel).collect(Collectors.toList());
    }

    public static List<FieldEntity> toDboList(List<Field> fields) {
        if (fields == null) return null;
        return fields.stream().map(FieldDboMapper::toDbo).collect(Collectors.toList());
    }
}
