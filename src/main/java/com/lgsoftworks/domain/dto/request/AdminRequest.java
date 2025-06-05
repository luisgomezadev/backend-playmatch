package com.lgsoftworks.domain.dto.request;

import com.lgsoftworks.domain.enums.DocumentType;
import com.lgsoftworks.domain.model.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdminRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private Byte age;
    private DocumentType documentType;
    private String documentNumber;
    private String email;
    private Field field;
}
