package com.lgsoftworks.domain.common.enums;

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
