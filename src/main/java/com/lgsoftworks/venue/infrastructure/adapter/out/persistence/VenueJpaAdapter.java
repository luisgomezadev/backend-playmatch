package com.lgsoftworks.venue.infrastructure.adapter.out.persistence;

import com.lgsoftworks.venue.domain.model.Venue;
import com.lgsoftworks.venue.domain.port.out.VenueRepositoryPort;
import com.lgsoftworks.venue.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.lgsoftworks.venue.infrastructure.adapter.out.persistence.mapper.VenueDboMapper;
import com.lgsoftworks.venue.infrastructure.adapter.out.persistence.repository.VenueRepository;
import com.lgsoftworks.venue.infrastructure.adapter.out.persistence.specifications.VenueSpecification;
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
    private final VenueDboMapper mapper;

    @Override
    public Venue save(Venue venue) {
        VenueEntity saved = venueRepository.save(mapper.toDbo(venue));
        return mapper.toModel(saved);
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return venueRepository.findById(id).map(mapper::toModel);
    }

    @Override
    public Optional<Venue> findByCode(String code) {
        return venueRepository.findByCode(code).map(mapper::toModel);
    }

    @Override
    public Optional<Venue> findByAdminId(Long adminId) {
        return venueRepository.findByAdminId(adminId).map(mapper::toModel);
    }

    @Override
    public Page<Venue> search(String name, String city, Pageable pageable) {
        Specification<VenueEntity> spec = Specification.allOf(
                VenueSpecification.hasName(name),
                VenueSpecification.hasCity(city)
        );

        return venueRepository.findAll(spec, pageable).map(mapper::toModel);
    }

    @Override
    public void deleteById(Long id) {
        venueRepository.deleteById(id);
    }
}