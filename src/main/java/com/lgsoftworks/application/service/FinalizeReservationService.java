package com.lgsoftworks.application.service;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.model.Reservation;
import com.lgsoftworks.domain.port.out.ReservationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinalizeReservationService {

    private final ReservationRepositoryPort reservationRepositoryPort;

    public void autoFinalizeExpiredReservations() {
        List<Reservation> activeReservations = reservationRepositoryPort.findAllByStatus(StatusReservation.ACTIVE);
        LocalDateTime now = LocalDateTime.now();

        for (Reservation reservation : activeReservations) {
            StatusReservation originalStatus = reservation.getStatus();
            reservation.finalizeIfExpired(now);

            if (reservation.getStatus() != originalStatus) {
                reservationRepositoryPort.save(reservation);
            }
        }
    }

}
