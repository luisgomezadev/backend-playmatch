package com.lgsoftworks.venue.domain.service;

import com.lgsoftworks.venue.domain.exception.DuplicateVenueCodeException;
import com.lgsoftworks.venue.domain.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VenueUniquenessValidator {

    private final VenueRepositoryPort venueRepositoryPort;

    public void validate(String code) {
        boolean exists = venueRepositoryPort.findByCode(code).isPresent();
        if (exists) {
            throw new DuplicateVenueCodeException(code);
        }
    }

    public void validateForUpdate(String code, Long excludeVenueId) {
        venueRepositoryPort.findByCode(code)
                .filter(venue -> !venue.getId().equals(excludeVenueId))
                .ifPresent(venue -> { throw new DuplicateVenueCodeException(code); });
    }
}