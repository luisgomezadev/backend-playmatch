package com.lgsoftworks.infrastructure.adapter.scheduler;

import com.lgsoftworks.application.service.FinalizeReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationFinalizerJob {

    private final FinalizeReservationService finalizeReservationService;

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void checkExpiredReservations() {
        finalizeReservationService.autoFinalizeExpiredReservations();
    }
}
