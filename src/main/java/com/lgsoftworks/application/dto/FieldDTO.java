package com.lgsoftworks.application.dto;

import com.lgsoftworks.application.dto.summary.ReservationFieldDTO;
import com.lgsoftworks.domain.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class FieldDTO {
    private Long id;
    private String name;
    private String city;
    private String address;
    private BigDecimal hourlyRate;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private UserDTO admin;
    private Status status;
    private String imageUrl;
}
