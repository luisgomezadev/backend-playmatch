package com.lgsoftworks.venue.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.lgsoftworks.venue.domain.model.Venue;
import com.lgsoftworks.venue.infrastructure.adapter.out.persistence.entity.VenueEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VenueDboMapper {

    private final EntityManager entityManager;

    public Venue toModel(VenueEntity entity) {
        if (entity == null) return null;
        return Venue.rehydrate(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getCity(),
                entity.getAddress(),
                entity.getOpeningHour(),
                entity.getClosingHour(),
                entity.getAdmin().getId(),
                entity.getStatus()
        );
    }

    public VenueEntity toDbo(Venue venue) {
        if (venue == null) return null;
        VenueEntity entity = new VenueEntity();
        entity.setId(venue.getId());
        entity.setCode(venue.getCode());
        entity.setName(venue.getName());
        entity.setCity(venue.getCity());
        entity.setAddress(venue.getAddress());
        entity.setOpeningHour(venue.getOpeningHour());
        entity.setClosingHour(venue.getClosingHour());
        entity.setStatus(venue.getStatus());
        entity.setAdmin(entityManager.getReference(UserEntity.class, venue.getAdminId()));
        return entity;
    }
}