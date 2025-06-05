package com.lgsoftworks.infrastructure.adapter.entity;

import com.lgsoftworks.domain.enums.DocumentType;
import com.lgsoftworks.domain.enums.Permission;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "admin")
@NoArgsConstructor
@Getter
@Setter
public class AdminEntity extends BaseEntity{
    private String firstName;
    private String lastName;
    private String city;
    private Byte age;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    @Column(unique = true)
    private String documentNumber;
    @Column(unique = true)
    @Email
    private String email;
    @OneToOne(mappedBy = "admin")
    private FieldEntity field;
}
