package com.lgsoftworks.domain.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum Permission {
    CREATE_TEAM("Crear equipo"),
    DELETE_TEAM("Eliminar equipo"),
    ASSIGN_MEMBER("Asignar miembro a equipo"),
    CREATE_FIELD("Crear campo"),
    DELETE_FIELD("Eliminar campo"),
    CREATE_RESERVATION("Crear reserva");

    private final String description;

    Permission(String description) {
        this.description = description;
    }

    // Lista de permisos por defecto para Admin
    public static final List<Permission> DEFAULT_ADMIN_PERMISSIONS = List.of(CREATE_FIELD,
            DELETE_FIELD,
            CREATE_RESERVATION);

    // Lista de permisos por defecto para Player
    public static final List<Permission> DEFAULT_PLAYER_PERMISSIONS = List.of(CREATE_TEAM,
            DELETE_TEAM,
            ASSIGN_MEMBER);
}
