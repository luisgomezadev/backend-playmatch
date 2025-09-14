package com.lgsoftworks.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.domain.venue.model.Venue;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.VenueEntity;

import java.util.List;
import java.util.stream.Collectors;

public class VenueDboMapper {

    public static Venue toModel(VenueEntity entity) {
        if (entity == null) return null;

        Venue venue = new Venue();
        venue.setId(entity.getId());
        venue.setCode(entity.getCode());
        venue.setName(entity.getName());
        venue.setCity(entity.getCity());
        venue.setAddress(entity.getAddress());
        venue.setOpeningHour(entity.getOpeningHour());
        venue.setClosingHour(entity.getClosingHour());
        venue.setAdmin(UserDboMapper.toModel(entity.getAdmin()));
        venue.setStatus(entity.getStatus());
        venue.setFields(FieldDboMapper.toModelList(entity.getFields()));

        return venue;
    }

    public static VenueEntity toDbo(Venue venue) {
        if (venue == null) return null;

        VenueEntity entity = new VenueEntity();
        entity.setId(venue.getId());
        entity.setCode(venue.getCode());
        entity.setName(venue.getName());
        entity.setCity(venue.getCity());
        entity.setAddress(venue.getAddress());
        entity.setOpeningHour(venue.getOpeningHour());
        entity.setClosingHour(venue.getClosingHour());
        entity.setAdmin(UserDboMapper.toDbo(venue.getAdmin()));
        entity.setStatus(venue.getStatus());

        if (venue.getFields() != null && !venue.getFields().isEmpty()) {
            List<FieldEntity> fieldEntities = FieldDboMapper.toDboList(venue.getFields());
            fieldEntities.forEach(f -> f.setVenue(entity));
            entity.setFields(fieldEntities);
        }

        return entity;
    }

    public static Venue toSimpleModel(VenueEntity entity) {
        if (entity == null) return null;

        Venue venue = new Venue();
        venue.setId(entity.getId());
        venue.setCode(entity.getCode());
        venue.setName(entity.getName());
        venue.setCity(entity.getCity());
        venue.setAddress(entity.getAddress());
        venue.setOpeningHour(entity.getOpeningHour());
        venue.setClosingHour(entity.getClosingHour());
        venue.setAdmin(UserDboMapper.toModel(entity.getAdmin()));
        venue.setStatus(entity.getStatus());
        // No cargamos campos completos para evitar ciclos
        venue.setFields(null);

        return venue;
    }

    public static VenueEntity toSimpleDbo(Venue venue) {
        if (venue == null) return null;

        VenueEntity entity = new VenueEntity();
        entity.setId(venue.getId());
        entity.setCode(venue.getCode());
        entity.setName(venue.getName());
        entity.setCity(venue.getCity());
        entity.setAddress(venue.getAddress());
        entity.setOpeningHour(venue.getOpeningHour());
        entity.setClosingHour(venue.getClosingHour());
        entity.setAdmin(UserDboMapper.toDbo(venue.getAdmin()));
        entity.setStatus(venue.getStatus());
        entity.setFields(null);

        return entity;
    }

    public static List<Venue> toModelList(List<VenueEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(VenueDboMapper::toModel).collect(Collectors.toList());
    }

    public static List<VenueEntity> toDboList(List<Venue> venues) {
        if (venues == null) return null;
        return venues.stream().map(VenueDboMapper::toDbo).collect(Collectors.toList());
    }
}
