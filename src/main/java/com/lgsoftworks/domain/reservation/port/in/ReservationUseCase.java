package com.lgsoftworks.domain.reservation.port.in;

import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.reservation.dto.request.ReservationFilter;
import com.lgsoftworks.application.reservation.dto.response.ReservationDTO;
import com.lgsoftworks.application.reservation.dto.request.ReservationRequest;
import com.lgsoftworks.domain.reservation.enums.StatusReservation;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReservationUseCase {
    PageResponse<ReservationDTO> searchReservations(ReservationFilter reservationFilter, Pageable pageable);
    Optional<ReservationDTO> findById(Long id);
    ReservationDTO save(ReservationRequest reservationRequest);
    ReservationDTO update(ReservationRequest reservationRequest);
    void deleteById(Long id);
    void updateStatus(Long id, StatusReservation status);
    List<ReservationDTO> findByFieldId(Long fieldId);
    List<ReservationDTO> findByFieldIdAndStatus(Long fieldId, StatusReservation status);
    List<ReservationDTO> findByUserId(Long userId);
    List<ReservationDTO> findLastThreeReservations(Long fieldId);
}
