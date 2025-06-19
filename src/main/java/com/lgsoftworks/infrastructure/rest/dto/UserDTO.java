package com.lgsoftworks.infrastructure.rest.dto;

import com.lgsoftworks.domain.enums.DocumentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private Byte age;
    private String cellphone;
    private DocumentType documentType;
    private String documentNumber;
    private String email;
}
