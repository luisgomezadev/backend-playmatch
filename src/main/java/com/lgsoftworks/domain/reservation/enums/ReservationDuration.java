package com.lgsoftworks.domain.reservation.enums;

import lombok.Getter;

@Getter
public enum ReservationDuration {
    MIN_60(60),
    MIN_90(90),
    MIN_120(120);

    private final int minutes;

    ReservationDuration(int minutes) {
        this.minutes = minutes;
    }
}
