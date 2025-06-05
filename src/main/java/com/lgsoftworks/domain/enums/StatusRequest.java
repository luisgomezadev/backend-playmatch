package com.lgsoftworks.domain.enums;

import lombok.Getter;

@Getter
public enum StatusRequest {
    ACCEPTED("Aceptada"),
    REJECTED("Rechazada"),
    PENDING("Pendiente");

    private final String description;

    StatusRequest(String description) {
        this.description = description;
    }
}
