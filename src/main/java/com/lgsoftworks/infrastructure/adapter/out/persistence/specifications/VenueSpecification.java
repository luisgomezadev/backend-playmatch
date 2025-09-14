package com.lgsoftworks.infrastructure.adapter.out.persistence.specifications;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.VenueEntity;
import org.springframework.data.jpa.domain.Specification;

public class VenueSpecification {
    public static Specification<VenueEntity> hasName(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<VenueEntity> hasCity(String city) {
        return (root, query, cb) ->
                city == null ? null : cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%");
    }

    public static Specification<VenueEntity> isActive() {
        return (root, query, cb) -> cb.equal(root.get("status"), Status.ACTIVE);
    }
}
