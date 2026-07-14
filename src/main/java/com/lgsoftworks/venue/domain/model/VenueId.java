package com.lgsoftworks.venue.domain.model;

import java.util.Objects;

public record VenueId(Long value) {
    public VenueId {
        Objects.requireNonNull(value, "VenueId no puede ser nulo");
    }
}