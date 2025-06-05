package com.lgsoftworks.domain.enums;

import lombok.Getter;

@Getter
public enum PersonType {

    ADMIN("Administrador"),
    PLAYER("Jugador");

    private final String description;

    PersonType(String description) {
        this.description = description;
    }

}
