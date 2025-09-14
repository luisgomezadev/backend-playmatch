package com.lgsoftworks.infrastructure.adapter.out.persistence;

import com.lgsoftworks.application.venue.dto.request.VenueFilter;
import com.lgsoftworks.domain.venue.model.Venue;
import com.lgsoftworks.domain.venue.port.out.VenueRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.VenueDboMapper;
import com.lgsoftworks.infrastructure.adapter.out.persistence.repository.VenueRepository;
import com.lgsoftworks.infrastructure.adapter.out.persistence.specifications.VenueSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VenueJpaAdapter implements VenueRepositoryPort {

    private final VenueRepository venueRepository;

    @Override
    public Optional<Venue> findById(Long id) {
        return venueRepository.findById(id)
                .map(VenueDboMapper::toModel);
    }

    @Override
    public Optional<Venue> findByAdminId(Long adminId) {
        return venueRepository.findByAdminId(adminId)
                .map(VenueDboMapper::toModel);
    }

    @Override
    public Optional<Venue> findByCode(String code) {
        return venueRepository.findByCode(code)
                .map(VenueDboMapper::toModel);
    }

    @Override
    public Page<Venue> searchVenues(VenueFilter filter, Pageable pageable) {
        Specification<VenueEntity> spec = Specification
                .where(VenueSpecification.isActive())
                .and(VenueSpecification.hasName(filter.getName()))
                .and(VenueSpecification.hasCity(filter.getCity()));

        return venueRepository.findAll(spec, pageable)
                .map(VenueDboMapper::toModel);
    }

    @Override
    public Venue save(Venue venue) {
        VenueEntity savedEntity = venueRepository.save(VenueDboMapper.toDbo(venue));
        return VenueDboMapper.toModel(savedEntity);
    }

    @Override
    public boolean existsByAdminId(Long adminId) {
        return venueRepository.existsByAdminId(adminId);
    }
}
