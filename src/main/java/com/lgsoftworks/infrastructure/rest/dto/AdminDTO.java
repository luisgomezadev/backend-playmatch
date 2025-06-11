package com.lgsoftworks.infrastructure.rest.dto;

import com.lgsoftworks.infrastructure.rest.dto.summary.FieldSummaryDTO;
import com.lgsoftworks.domain.enums.DocumentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdminDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private Byte age;
    private String cellphone;
    private DocumentType documentType;
    private String documentNumber;
    private String email;
    private FieldSummaryDTO field;
}
