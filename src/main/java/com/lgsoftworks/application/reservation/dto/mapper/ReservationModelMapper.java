package com.lgsoftworks.application.reservation.dto.mapper;

import com.lgsoftworks.application.field.dto.mapper.FieldModelMapper;
import com.lgsoftworks.application.user.dto.mapper.UserModelMapper;
import com.lgsoftworks.application.reservation.dto.response.ReservationDTO;
import com.lgsoftworks.application.reservation.dto.request.ReservationRequest;
import com.lgsoftworks.application.reservation.dto.response.ReservationAvailabilityDTO;
import com.lgsoftworks.domain.reservation.enums.StatusReservation;
import com.lgsoftworks.domain.reservation.model.Reservation;

public class ReservationModelMapper {

    public static Reservation toModel(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setHours(reservationDTO.getHours());
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setUser(UserModelMapper.toUser(reservationDTO.getUser()));
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setStatus(reservationDTO.getStatus());
        reservation.setField(FieldModelMapper.toModel(reservationDTO.getField()));
        return reservation;
    }

    public static Reservation toModelOfAvailability(ReservationAvailabilityDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setHours(reservationDTO.getHours());
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setUser(UserModelMapper.toUser(reservationDTO.getUser()));
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setStatus(StatusReservation.ACTIVE);
        reservation.setField(FieldModelMapper.toModel(reservationDTO.getField()));
        return reservation;
    }

    public static Reservation toModelRequest(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationRequest.getId());
        reservation.setHours(reservationRequest.getHours());
        reservation.setReservationDate(reservationRequest.getReservationDate());
        reservation.setStartTime(reservationRequest.getStartTime());
        reservation.setStatus(reservationRequest.getStatus());
        return reservation;
    }

    public static ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setHours(reservation.getHours());
        reservationDTO.setField(FieldModelMapper.toDTO(reservation.getField()));
        reservationDTO.setReservationDate(reservation.getReservationDate());
        reservationDTO.setStartTime(reservation.getStartTime());
        reservationDTO.setEndTime(reservation.getEndTime());
        reservationDTO.setStatus(reservation.getStatus());
        reservationDTO.setUser(UserModelMapper.toUserDTO(reservation.getUser()));
        return reservationDTO;
    }

    public static ReservationAvailabilityDTO toAvailabilityDTO(Reservation reservation) {
        ReservationAvailabilityDTO reservationDTO = new ReservationAvailabilityDTO();
        reservationDTO.setHours(reservation.getHours());
        reservationDTO.setField(FieldModelMapper.toDTO(reservation.getField()));
        reservationDTO.setReservationDate(reservation.getReservationDate());
        reservationDTO.setStartTime(reservation.getStartTime());
        reservationDTO.setEndTime(reservation.getEndTime());
        reservationDTO.setUser(UserModelMapper.toUserDTO(reservation.getUser()));
        return reservationDTO;
    }

}
