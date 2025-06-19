package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.infrastructure.rest.dto.ReservationDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.ReservationRequest;

import java.util.List;
import java.util.Optional;

public interface ReservationUseCase {
    List<ReservationDTO> findAll();
    Optional<ReservationDTO> findById(Long id);
    ReservationDTO save(ReservationRequest reservationRequest);
    ReservationDTO update(ReservationRequest reservationRequest);
    void deleteById(Long id);
    void updateStatus(Long id, StatusReservation status);
    List<ReservationDTO> findByFieldId(Long fieldId);
    List<ReservationDTO> findByTeamId(Long teamId);
    List<ReservationDTO> findAllByStatus(StatusReservation status);
}
