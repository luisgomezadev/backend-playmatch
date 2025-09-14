package com.lgsoftworks.domain.reservation.port.out;

import com.lgsoftworks.application.reservation.dto.request.ReservationFilter;
import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.reservation.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepositoryPort {
    Optional<Reservation> findById(Long id);
    Optional<Reservation> findByCode(String code);
    Reservation save(Reservation reservation);
    void updateStatus(Long reservationId, Status status);
    List<Reservation> findByFieldId(Long fieldId);
    List<Reservation> findByVenueId(Long venueId);
    List<Reservation> findByFieldIdAndDate(Long fieldId, LocalDate date);
    List<Reservation> findByVenueIdAndDate(Long venueId, LocalDate date);
}
