package com.lgsoftworks.domain.reservation.port.out;

import com.lgsoftworks.application.reservation.dto.request.ReservationFilter;
import com.lgsoftworks.domain.reservation.enums.StatusReservation;
import com.lgsoftworks.domain.reservation.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepositoryPort {
    Page<Reservation> searchReservations(ReservationFilter reservationFilter, Pageable pageable);
    Optional<Reservation> findById(Long id);
    Reservation save(Reservation reservation);
    void deleteById(Long id);
    void updateStatus(Long reservationId, StatusReservation status);
    List<Reservation> findByFieldId(Long fieldId);
    List<Reservation> findByFieldIdAndStatus(Long fieldId, StatusReservation status);
    List<Reservation> findActiveByFieldIdAndDate(Long fieldId, LocalDate date);
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findAllByStatus(StatusReservation status);
    List<Reservation> findLastThreeReservations(Long fieldId);
    Long countReservationsByUserAndStatus(StatusReservation statusReservation, Long userId);
    Long countReservationsByFieldAndStatus(StatusReservation statusReservation, Long fieldId);
}
