package com.lgsoftworks.reservation.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.field.infrastructure.adapter.out.persistence.entity.FieldEntity;
import com.lgsoftworks.reservation.domain.model.Reservation;
import com.lgsoftworks.reservation.infrastructure.adapter.out.persistence.entity.ReservationEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationDboMapper {

    private final EntityManager entityManager;

    public Reservation toModel(ReservationEntity entity) {
        if (entity == null) return null;
        return Reservation.rehydrate(
                entity.getId(),
                entity.getCode(),
                entity.getCustomerName(),
                entity.getCellphone(),
                entity.getField().getId(),
                entity.getDuration(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getReservationDate(),
                entity.getStatus()
        );
    }

    public ReservationEntity toDbo(Reservation reservation) {
        if (reservation == null) return null;
        ReservationEntity entity = new ReservationEntity();
        entity.setId(reservation.getId());
        entity.setCode(reservation.getCode());
        entity.setCustomerName(reservation.getCustomerName());
        entity.setCellphone(reservation.getCellphone());
        entity.setDuration(reservation.getDuration());
        entity.setStartTime(reservation.getStartTime());
        entity.setEndTime(reservation.getEndTime());
        entity.setReservationDate(reservation.getReservationDate());
        entity.setStatus(reservation.getStatus());
        entity.setField(entityManager.getReference(FieldEntity.class, reservation.getFieldId()));
        return entity;
    }
}