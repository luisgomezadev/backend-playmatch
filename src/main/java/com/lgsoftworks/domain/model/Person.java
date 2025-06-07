package com.lgsoftworks.domain.model;

import com.lgsoftworks.domain.enums.DocumentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private Byte age;
    private DocumentType documentType;
    private String documentNumber;
    private String email;
}
