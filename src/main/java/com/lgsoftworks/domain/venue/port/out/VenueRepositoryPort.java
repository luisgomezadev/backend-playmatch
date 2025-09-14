package com.lgsoftworks.domain.venue.port.out;

import com.lgsoftworks.application.venue.dto.request.VenueFilter;
import com.lgsoftworks.domain.venue.model.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VenueRepositoryPort {
    Optional<Venue> findById(Long id);

    Optional<Venue> findByAdminId(Long adminId);

    Optional<Venue> findByCode(String code);

    Page<Venue> searchVenues(VenueFilter filter, Pageable pageable);

    Venue save(Venue venue);

    boolean existsByAdminId(Long adminId);
}
