package com.lgsoftworks.application.venue.dto.response;

import com.lgsoftworks.application.field.dto.response.FieldDTO;
import com.lgsoftworks.application.user.dto.response.UserDTO;
import com.lgsoftworks.domain.common.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class VenueDTO {
    private Long id;
    private String code;
    private String name;
    private String city;
    private String address;
    private UserDTO admin;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private Status status;
    private List<FieldDTO> fields;
}
