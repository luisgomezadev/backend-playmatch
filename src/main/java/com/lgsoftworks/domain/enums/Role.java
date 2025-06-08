package com.lgsoftworks.domain.enums;

import lombok.Getter;

@Getter
public enum Role {
    PLAYER("Player"),
    ADMIN("Admin");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
