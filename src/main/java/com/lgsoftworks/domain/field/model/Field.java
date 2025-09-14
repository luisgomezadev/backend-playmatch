package com.lgsoftworks.domain.field.model;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.field.enums.FieldType;
import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.domain.venue.model.Venue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Field {
    private Long id;
    private String name;
    private FieldType fieldType;
    private BigDecimal hourlyRate;
    private Venue venue;
    private List<Reservation> reservations = new ArrayList<>();
}
