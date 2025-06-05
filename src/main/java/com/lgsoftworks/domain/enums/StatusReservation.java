package com.lgsoftworks.domain.enums;

import lombok.Getter;

@Getter
public enum StatusReservation {
    ACTIVE("Activa"),
    FINISHED("Finalizada");

    private final String description;

    StatusReservation(String description) {
        this.description = description;
    }
}
