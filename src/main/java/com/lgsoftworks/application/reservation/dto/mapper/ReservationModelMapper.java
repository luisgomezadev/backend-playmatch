package com.lgsoftworks.application.reservation.dto.mapper;

import com.lgsoftworks.application.reservation.dto.response.ReservationDTO;
import com.lgsoftworks.application.reservation.dto.request.ReservationRequest;
import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.domain.venue.model.Venue;

public class ReservationModelMapper {

    public static Reservation toModel(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setCode(reservationDTO.getCode());
        reservation.setDuration(reservationDTO.getDuration());
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setUser(reservationDTO.getUser());
        reservation.setCellphone(reservationDTO.getCellphone());
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setStatus(reservationDTO.getStatus());

        if (reservationDTO.getFieldId() != null) {
            Field field = new Field();
            field.setId(reservationDTO.getFieldId());
            reservation.setField(field);
        }

        return reservation;
    }

    public static Reservation toModelRequest(ReservationRequest reservationRequest) {
        if (reservationRequest == null) return null;

        Reservation reservation = new Reservation();
        reservation.setId(reservationRequest.getId());
        reservation.setUser(reservationRequest.getUser());
        reservation.setCellphone(reservationRequest.getCellphone());
        reservation.setDuration(reservationRequest.getDuration());
        reservation.setStartTime(reservationRequest.getStartTime());
        reservation.setReservationDate(reservationRequest.getReservationDate());

        if (reservationRequest.getFieldId() != null) {
            Field field = new Field();
            field.setId(reservationRequest.getFieldId());
            reservation.setField(field);
        }

        return reservation;
    }

    public static ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setCode(reservation.getCode());
        reservationDTO.setUser(reservation.getUser());
        reservationDTO.setCellphone(reservation.getCellphone());
        reservationDTO.setDuration(reservation.getDuration());
        reservationDTO.setReservationDate(reservation.getReservationDate());
        reservationDTO.setStartTime(reservation.getStartTime());
        reservationDTO.setEndTime(reservation.getEndTime());
        reservationDTO.setStatus(reservation.getStatus());

        if (reservation.getField() != null) {
            Field field = reservation.getField();
            reservationDTO.setFieldId(field.getId());
            reservationDTO.setFieldName(field.getName());

            if (field.getVenue() != null) {
                Venue venue = field.getVenue();
                reservationDTO.setVenueId(venue.getId());
                reservationDTO.setVenueName(venue.getName());
            }
        }
        return reservationDTO;
    }

}
