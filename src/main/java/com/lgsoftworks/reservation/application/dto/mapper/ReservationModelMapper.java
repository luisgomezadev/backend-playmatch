package com.lgsoftworks.reservation.application.dto.mapper;

import com.lgsoftworks.reservation.application.dto.response.ReservationDTO;
import com.lgsoftworks.reservation.domain.model.Reservation;

public class ReservationModelMapper {

    private ReservationModelMapper() {
    }

    public static ReservationDTO toDTO(Reservation reservation) {
        if (reservation == null) return null;
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setCode(reservation.getCode());
        dto.setCustomerName(reservation.getCustomerName());
        dto.setCellphone(reservation.getCellphone());
        dto.setFieldId(reservation.getFieldId());
        dto.setDuration(reservation.getDuration());
        dto.setStartTime(reservation.getStartTime());
        dto.setEndTime(reservation.getEndTime());
        dto.setReservationDate(reservation.getReservationDate());
        dto.setStatus(reservation.getStatus());
        return dto;
    }
}