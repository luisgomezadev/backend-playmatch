package com.lgsoftworks.infrastructure.adapter.out.persistence;
import com.lgsoftworks.domain.enums.Status;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class FieldSpecification {

    public static Specification<FieldEntity> hasCity(String city) {
        return (root, query, cb) ->
                city == null ? null : cb.equal(cb.lower(root.get("city")), city.toLowerCase());
    }

    public static Specification<FieldEntity> hasMinPrice(BigDecimal minPrice) {
        return (root, query, cb) ->
                minPrice == null ? null : cb.greaterThanOrEqualTo(root.get("hourlyRate"), minPrice);
    }

    public static Specification<FieldEntity> hasMaxPrice(BigDecimal maxPrice) {
        return (root, query, cb) ->
                maxPrice == null ? null : cb.lessThanOrEqualTo(root.get("hourlyRate"), maxPrice);
    }

    public static Specification<FieldEntity> isActive() {
        return (root, query, cb) -> cb.equal(root.get("status"), Status.ACTIVE);
    }

}
