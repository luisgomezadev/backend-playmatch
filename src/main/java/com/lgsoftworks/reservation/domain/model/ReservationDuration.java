package com.lgsoftworks.reservation.domain.model;

import lombok.Getter;

@Getter
public enum ReservationDuration {
    MIN_60(60),
    MIN_90(90),
    MIN_120(120),
    MIN_150(150),
    MIN_180(180);

    private final int minutes;

    ReservationDuration(int minutes) {
        this.minutes = minutes;
    }
}
