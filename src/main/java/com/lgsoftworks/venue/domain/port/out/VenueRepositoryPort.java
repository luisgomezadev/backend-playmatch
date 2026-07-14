package com.lgsoftworks.venue.domain.port.out;

import com.lgsoftworks.venue.domain.model.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VenueRepositoryPort {
    Venue save(Venue venue);
    Optional<Venue> findById(Long id);
    Optional<Venue> findByCode(String code);
    Optional<Venue> findByAdminId(Long adminId);
    Page<Venue> search(String name, String city, Pageable pageable);
    void deleteById(Long id);
}