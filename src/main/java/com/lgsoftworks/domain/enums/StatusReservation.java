package com.lgsoftworks.domain.enums;

import lombok.Getter;

@Getter
public enum StatusReservation {
    ACTIVE("Activa"),
    CANCELED("Cancelada"),
    FINISHED("Finalizada");

    private final String description;

    StatusReservation(String description) {
        this.description = description;
    }
}
