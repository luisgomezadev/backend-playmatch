package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.domain.dto.ReservationDTO;
import com.lgsoftworks.domain.dto.request.ReservationRequest;

import java.util.List;
import java.util.Optional;

public interface ReservationUseCase {
    List<ReservationDTO> findAll();
    Optional<ReservationDTO> findById(Long id);
    ReservationDTO save(ReservationRequest reservationRequest);
    ReservationDTO update(ReservationRequest reservationRequest);
    void deleteById(Long id);
    void finalizeReservation(Long id);
}
