package com.lgsoftworks.application.field.dto.response;

import com.lgsoftworks.application.user.dto.response.UserDTO;
import com.lgsoftworks.domain.common.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

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
