package com.lgsoftworks.infrastructure.adapter.out.persistence.specifications;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.ReservationEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ReservationSpecification {
    public static Specification<ReservationEntity> hasDate(LocalDate date) {
        return (root, query, cb) ->
                date == null ? null : cb.equal(root.get("reservationDate"), date);
    }

    public static Specification<ReservationEntity> hasUserId(Long userId) {
        return (root, query, cb) ->
                userId == null ? null : cb.equal(root.get("user").get("id"), userId);
    }

    public static Specification<ReservationEntity> hasFieldId(Long fieldId) {
        return (root, query, cb) ->
                fieldId == null ? null : cb.equal(root.get("field").get("id"), fieldId);
    }
}
