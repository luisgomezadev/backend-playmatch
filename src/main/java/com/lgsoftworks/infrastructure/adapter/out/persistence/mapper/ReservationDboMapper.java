package com.lgsoftworks.infrastructure.adapter.out.persistence.mapper;

import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.ReservationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationDboMapper {
    public static Reservation toModel(ReservationEntity reservationEntity) {
        if (reservationEntity == null) return null;
        Reservation reservation = new Reservation();
        reservation.setId(reservationEntity.getId());
        reservation.setHours(reservationEntity.getHours());
        reservation.setReservationDate(reservationEntity.getReservationDate());
        reservation.setUser(UserDboMapper.toModel(reservationEntity.getUser()));
        reservation.setStartTime(reservationEntity.getStartTime());
        reservation.setEndTime(reservationEntity.getEndTime());
        reservation.setStatus(reservationEntity.getStatus());
        reservation.setField(FieldDboMapper.toSimpleModel(reservationEntity.getField()));
        return reservation;
    }

    public static ReservationEntity toDbo(Reservation reservation) {
        if (reservation == null) return null;
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(reservation.getId());
        reservationEntity.setHours(reservation.getHours());
        reservationEntity.setReservationDate(reservation.getReservationDate());
        reservationEntity.setUser(UserDboMapper.toDbo(reservation.getUser()));
        reservationEntity.setStartTime(reservation.getStartTime());
        reservationEntity.setEndTime(reservation.getEndTime());
        reservationEntity.setStatus(reservation.getStatus());
        reservationEntity.setField(FieldDboMapper.toSimpleDbo(reservation.getField()));
        return reservationEntity;
    }

    public static Reservation toSimpleModel(ReservationEntity reservationEntity) {
        if (reservationEntity == null) return null;
        Reservation reservation = new Reservation();
        reservation.setId(reservationEntity.getId());
        reservation.setHours(reservationEntity.getHours());
        reservation.setReservationDate(reservationEntity.getReservationDate());
        reservation.setStartTime(reservationEntity.getStartTime());
        reservation.setEndTime(reservationEntity.getEndTime());
        reservation.setStatus(reservationEntity.getStatus());
        reservation.setField(FieldDboMapper.toSimpleModel(reservationEntity.getField()));
        reservation.setUser(UserDboMapper.toModel(reservationEntity.getUser()));
        return reservation;
    }

    public static ReservationEntity toSimpleDbo(Reservation reservation) {
        if (reservation == null) return null;
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(reservation.getId());
        reservationEntity.setHours(reservation.getHours());
        reservationEntity.setReservationDate(reservation.getReservationDate());
        reservationEntity.setStartTime(reservation.getStartTime());
        reservationEntity.setEndTime(reservation.getEndTime());
        reservationEntity.setStatus(reservation.getStatus());
        reservationEntity.setField(FieldDboMapper.toSimpleDbo(reservation.getField()));
        reservationEntity.setUser(UserDboMapper.toDbo(reservation.getUser()));
        return reservationEntity;
    }

    public static List<Reservation> toModelList(List<ReservationEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(ReservationDboMapper::toSimpleModel)
                .collect(Collectors.toList());
    }

    public static List<ReservationEntity> toDboList(List<Reservation> reservations) {
        if (reservations == null) return null;
        return reservations.stream()
                .map(ReservationDboMapper::toSimpleDbo)
                .collect(Collectors.toList());
    }
}
