package com.lgsoftworks.domain.enums;

import lombok.Getter;

@Getter
public enum DocumentType {
    CC("Cédula de ciudadanía"),
    TI("Tarjeta de identidad"),
    PA("Pasaporte");

    private final String description;

    DocumentType(String description) {
        this.description = description;
    }

}
