package com.lgsoftworks.venue.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class VenuePublicDTO {
    private Long id;
    private String code;
    private String name;
    private String city;
    private String address;
    private LocalTime openingHour;
    private LocalTime closingHour;
}
