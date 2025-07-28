package com.lgsoftworks.application.mapper;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.application.dto.ReservationDTO;
import com.lgsoftworks.application.dto.request.ReservationRequest;
import com.lgsoftworks.application.dto.ReservationAvailabilityDTO;
import com.lgsoftworks.application.dto.summary.ReservationFieldDTO;
import com.lgsoftworks.domain.model.Reservation;

import java.util.List;
import java.util.stream.Collectors;

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
        reservation.setField(FieldModelMapper.dtoSummaryToField(reservationDTO.getField()));
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
        reservation.setField(FieldModelMapper.dtoSummaryToField(reservationDTO.getField()));
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
        reservationDTO.setField(FieldModelMapper.toFieldSummaryDTO(reservation.getField()));
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
        reservationDTO.setField(FieldModelMapper.toFieldSummaryDTO(reservation.getField()));
        reservationDTO.setReservationDate(reservation.getReservationDate());
        reservationDTO.setStartTime(reservation.getStartTime());
        reservationDTO.setEndTime(reservation.getEndTime());
        reservationDTO.setUser(UserModelMapper.toUserDTO(reservation.getUser()));
        return reservationDTO;
    }

    public static ReservationFieldDTO toReservationFieldDTO(Reservation reservation) {
        if (reservation == null) return null;
        ReservationFieldDTO reservationDTO = new ReservationFieldDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setHours(reservation.getHours());
        reservationDTO.setReservationDate(reservation.getReservationDate());
        reservationDTO.setUser(UserModelMapper.toUserDTO(reservation.getUser()));
        reservationDTO.setStartTime(reservation.getStartTime());
        reservationDTO.setEndTime(reservation.getEndTime());
        reservationDTO.setStatus(reservation.getStatus());
        return reservationDTO;
    }

    public static Reservation toReservationField(ReservationFieldDTO reservationFieldDTO) {
        if (reservationFieldDTO == null) return null;
        Reservation reservation = new Reservation();
        reservation.setId(reservationFieldDTO.getId());
        reservation.setHours(reservationFieldDTO.getHours());
        reservation.setReservationDate(reservationFieldDTO.getReservationDate());
        reservation.setUser(UserModelMapper.toUser(reservationFieldDTO.getUser()));
        reservation.setStartTime(reservationFieldDTO.getStartTime());
        reservation.setEndTime(reservationFieldDTO.getEndTime());
        reservation.setStatus(reservationFieldDTO.getStatus());
        return reservation;
    }

    public static List<ReservationFieldDTO> toReservationFieldDTOList(List<Reservation> reservations) {
        if (reservations == null) return null;
        return reservations.stream()
                .map(ReservationModelMapper::toReservationFieldDTO)
                .collect(Collectors.toList());
    }

    public static List<Reservation> toReservationFieldList(List<ReservationFieldDTO> reservations) {
        if (reservations == null) return null;
        return reservations.stream()
                .map(ReservationModelMapper::toReservationField)
                .collect(Collectors.toList());
    }

}
