package com.lgsoftworks.venue.infrastructure.adapter.out.persistence.specifications;

import com.lgsoftworks.venue.infrastructure.adapter.out.persistence.entity.VenueEntity;
import org.springframework.data.jpa.domain.Specification;

public class VenueSpecification {
    private VenueSpecification() {
    }

    public static Specification<VenueEntity> hasName(String name) {
        return (root, query, cb) -> name == null ? null :
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<VenueEntity> hasCity(String city) {
        return (root, query, cb) -> city == null ? null :
                cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%");
    }
}
