package com.lgsoftworks.domain.venue.model;

import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.reservation.model.Reservation;
import com.lgsoftworks.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Venue {
    private Long id;
    private String code;
    private String name;
    private String city;
    private String address;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private User admin;
    private Status status;
    private List<Field> fields = new ArrayList<>();
}
