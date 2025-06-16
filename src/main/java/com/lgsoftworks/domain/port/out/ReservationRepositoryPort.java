package com.lgsoftworks.domain.port.out;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepositoryPort {
    List<Reservation> findAll();
    Optional<Reservation> findById(Long id);
    Reservation save(Reservation reservation);
    Reservation update(Reservation reservation);
    void deleteById(Long id);
    List<Reservation> findByFieldId(Long fieldId);
    List<Reservation> findByTeamId(Long teamId);
    List<Reservation> findAllByStatus(StatusReservation status);
    Long countActiveReservationsByTeam(StatusReservation statusReservation, Long teamId);
    Long countFinishedReservationsByTeam(StatusReservation statusReservation, Long teamId);
    Long countActiveReservationsByField(StatusReservation statusReservation, Long fieldId);
    Long countFinishedReservationsByField(StatusReservation statusReservation, Long fieldId);
}
