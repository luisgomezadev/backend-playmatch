package com.lgsoftworks.field.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.field.domain.model.Field;
import com.lgsoftworks.field.infrastructure.adapter.out.persistence.entity.FieldEntity;
import com.lgsoftworks.venue.domain.model.VenueId;
import com.lgsoftworks.venue.infrastructure.adapter.out.persistence.entity.VenueEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FieldDboMapper {

    private final EntityManager entityManager;

    public Field toModel(FieldEntity entity) {
        if (entity == null) return null;
        return Field.rehydrate(
                entity.getId(),
                entity.getName(),
                entity.getFieldType(),
                entity.getHourlyRate(),
                new VenueId(entity.getVenue().getId()),
                entity.isActive()
        );
    }

    public FieldEntity toDbo(Field field) {
        if (field == null) return null;
        FieldEntity entity = new FieldEntity();
        entity.setId(field.getId());
        entity.setName(field.getName());
        entity.setFieldType(field.getFieldType());
        entity.setHourlyRate(field.getHourlyRate());
        entity.setActive(field.isActive());
        entity.setVenue(entityManager.getReference(VenueEntity.class, field.getVenueId().value()));
        return entity;
    }
}