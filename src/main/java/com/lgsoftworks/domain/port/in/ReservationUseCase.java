package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.PageResponse;
import com.lgsoftworks.application.dto.request.ReservationFilter;
import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.application.dto.ReservationDTO;
import com.lgsoftworks.application.dto.request.ReservationRequest;
import com.lgsoftworks.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationUseCase {
    List<ReservationDTO> findAll();
    PageResponse<ReservationDTO> searchReservations(ReservationFilter reservationFilter, Pageable pageable);
    Optional<ReservationDTO> findById(Long id);
    ReservationDTO save(ReservationRequest reservationRequest);
    ReservationDTO update(ReservationRequest reservationRequest);
    void deleteById(Long id);
    void updateStatus(Long id, StatusReservation status);
    List<ReservationDTO> findByFieldId(Long fieldId);
    List<ReservationDTO> findByFieldIdAndStatus(Long fieldId, StatusReservation status);
    List<ReservationDTO> findByUserId(Long userId);
    List<ReservationDTO> findAllByStatus(StatusReservation status);
    List<ReservationDTO> findLastThreeReservations(Long fieldId);
}
