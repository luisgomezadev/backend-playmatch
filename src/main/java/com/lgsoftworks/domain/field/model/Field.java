package com.lgsoftworks.domain.field.model;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Field {
    private Long id;
    private String name;
    private String city;
    private String address;
    private BigDecimal hourlyRate;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private User admin;
    private Status status;
    private String imageUrl;
    private List<Reservation> reservations = new ArrayList<>();

}
