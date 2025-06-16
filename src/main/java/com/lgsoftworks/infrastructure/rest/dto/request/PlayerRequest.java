package com.lgsoftworks.infrastructure.rest.dto.request;

import com.lgsoftworks.domain.enums.DocumentType;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.model.Team;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
public class PlayerRequest {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String firstName;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastName;

    @NotBlank(message = "La ciudad no puede estar vacía")
    private String city;

    @NotNull(message = "La edad no puede ser nula")
    private Byte age;

    @NotBlank(message = "El número de celular no puede estar vacío")
    private String cellphone;

    private DocumentType documentType;

    @NotBlank(message = "El número de documento no puede estar vacío")
    private String documentNumber;

    @NotBlank(message = "El email no puede estar vacío")
    private String email;
    private Team team;
    @Length(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    private Role role;
}
