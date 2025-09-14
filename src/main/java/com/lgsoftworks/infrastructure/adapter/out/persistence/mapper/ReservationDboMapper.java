package com.lgsoftworks.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.ReservationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationDboMapper {

    public static Reservation toModel(ReservationEntity entity) {
        if (entity == null) return null;

        Reservation reservation = new Reservation();
        reservation.setId(entity.getId());
        reservation.setCode(entity.getCode());
        reservation.setUser(entity.getUser());
        reservation.setCellphone(entity.getCellphone());
        reservation.setField(FieldDboMapper.toSimpleModel(entity.getField()));
        reservation.setDuration(entity.getDuration());
        reservation.setStartTime(entity.getStartTime());
        reservation.setEndTime(entity.getEndTime());
        reservation.setReservationDate(entity.getReservationDate());
        reservation.setStatus(entity.getStatus());

        return reservation;
    }

    public static ReservationEntity toDbo(Reservation reservation) {
        if (reservation == null) return null;

        ReservationEntity entity = new ReservationEntity();
        entity.setId(reservation.getId());
        entity.setCode(reservation.getCode());
        entity.setUser(reservation.getUser());
        entity.setCellphone(reservation.getCellphone());
        entity.setField(FieldDboMapper.toSimpleDbo(reservation.getField()));
        entity.setDuration(reservation.getDuration());
        entity.setStartTime(reservation.getStartTime());
        entity.setEndTime(reservation.getEndTime());
        entity.setReservationDate(reservation.getReservationDate());
        entity.setStatus(reservation.getStatus());

        return entity;
    }

    public static List<Reservation> toModelList(List<ReservationEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(ReservationDboMapper::toModel)
                .collect(Collectors.toList());
    }

    public static List<ReservationEntity> toDboList(List<Reservation> reservations) {
        if (reservations == null) return null;
        return reservations.stream()
                .map(ReservationDboMapper::toDbo)
                .collect(Collectors.toList());
    }
}
