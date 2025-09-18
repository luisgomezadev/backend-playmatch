package com.lgsoftworks.domain.reservation.port.in;

import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.reservation.dto.request.ReservationFilter;
import com.lgsoftworks.application.reservation.dto.response.ReservationDTO;
import com.lgsoftworks.application.reservation.dto.request.ReservationRequest;
import com.lgsoftworks.domain.common.enums.Status;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationUseCase {
    Optional<ReservationDTO> findById(Long id);
    Optional<ReservationDTO> findByCode(String code);
    ReservationDTO save(ReservationRequest reservationRequest);
    void updateStatus(Long reservationId, Status status);
    List<ReservationDTO> findByFieldId(Long fieldId);
    List<ReservationDTO> findByVenueId(Long venueId);
    List<ReservationDTO> findByVenueIdAndDate(Long venueId, LocalDate date);
}
