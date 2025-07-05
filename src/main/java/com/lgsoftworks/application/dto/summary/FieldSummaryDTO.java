package com.lgsoftworks.application.dto.summary;

import com.lgsoftworks.domain.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class FieldSummaryDTO {
    private Long id;
    private String name;
    private String city;
    private String address;
    private BigDecimal hourlyRate;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private Status status;
    private String imageUrl;
}
