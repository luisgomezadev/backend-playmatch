package com.lgsoftworks.domain.dto.request;

import com.lgsoftworks.domain.enums.Status;
import com.lgsoftworks.domain.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FieldRequest {
    private Long id;
    private String name;
    private String city;
    private String address;
    private BigDecimal hourlyRate;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private Admin admin;
    private Status status;
}
