package com.lgsoftworks.domain.port.out;

import com.lgsoftworks.domain.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepositoryPort {
    List<Reservation> findAll();
    Optional<Reservation> findById(Long id);
    Reservation save(Reservation reservation);
    Reservation update(Reservation reservation);
    void deleteById(Long id);
}
