package com.lgsoftworks.venue.domain.model;

import com.lgsoftworks.shared.domain.enums.Status;
import com.lgsoftworks.venue.domain.exception.InvalidVenueDataException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Objects;

@Getter
@EqualsAndHashCode(of = "id")
public class Venue {
    private Long id;
    private String code;
    private String name;
    private String city;
    private String address;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private Long adminId;
    private Status status;

    protected Venue() {
    }

    public static Venue create(String code, String name, String city, String address,
                               LocalTime openingHour, LocalTime closingHour, Long adminId) {
        Venue venue = new Venue();
        venue.code = requireNonBlank(code, "El código del complejo deportivo es requerido");
        venue.name = requireNonBlank(name, "El nombre del complejo deportivo es requerido");
        venue.city = requireNonBlank(city, "La ciudad es requerida");
        venue.address = requireNonBlank(address, "La dirección es requerida");
        validateSchedule(openingHour, closingHour);
        venue.openingHour = openingHour;
        venue.closingHour = closingHour;
        venue.adminId = Objects.requireNonNull(adminId, "El complejo deportivo debe tener un administrador");
        venue.status = Status.ACTIVE;
        return venue;
    }

    public static Venue rehydrate(Long id, String code, String name, String city, String address,
                                  LocalTime openingHour, LocalTime closingHour,
                                  Long adminId, Status status) {
        Venue venue = new Venue();
        venue.id = id;
        venue.code = code;
        venue.name = name;
        venue.city = city;
        venue.address = address;
        venue.openingHour = openingHour;
        venue.closingHour = closingHour;
        venue.adminId = adminId;
        venue.status = status;
        return venue;
    }

    public void rename(String newName) {
        this.name = requireNonBlank(newName, "El nombre del venue es requerido");
    }

    public void changeAddress(String newCity, String newAddress) {
        this.city = requireNonBlank(newCity, "La ciudad es requerida");
        this.address = requireNonBlank(newAddress, "La dirección es requerida");
    }

    public void changeCode(String newCode) {
        this.code = requireNonBlank(newCode, "El código del complejo deportivo es requerido");
    }

    public void changeSchedule(LocalTime newOpeningHour, LocalTime newClosingHour) {
        validateSchedule(newOpeningHour, newClosingHour);
        this.openingHour = newOpeningHour;
        this.closingHour = newClosingHour;
    }

    public void deactivate() {
        this.status = Status.INACTIVE;
    }

    public void activate() {
        this.status = Status.ACTIVE;
    }

    private static void validateSchedule(LocalTime opening, LocalTime closing) {
        if (opening == null || closing == null) {
            throw new InvalidVenueDataException("El horario de apertura y cierre son requeridos");
        }
        if (!opening.isBefore(closing)) {
            throw new InvalidVenueDataException("La hora de apertura debe ser antes que la de cierre");
        }
    }

    private static String requireNonBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new InvalidVenueDataException(message);
        }
        return value;
    }

}