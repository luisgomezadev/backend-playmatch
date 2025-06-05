package com.lgsoftworks.domain.enums;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("Activo"),
    INACTIVE("Inactivo");

    private final String description;

    Status(String description) {
        this.description = description;
    }
}
