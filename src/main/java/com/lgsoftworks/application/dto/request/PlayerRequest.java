package com.lgsoftworks.application.dto.request;

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

    @NotBlank(message = "El nombre es requerido")
    private String firstName;

    @NotBlank(message = "El apellido es requerido")
    private String lastName;

    @NotBlank(message = "La ciudad es requerida")
    private String city;

    @NotNull(message = "La edad es requerida")
    private Byte age;

    @NotBlank(message = "El número de celular es requerido")
    @Length(min = 6, message = "El celular debe tener al menos 6 números")
    private String cellphone;

    private DocumentType documentType;

    @NotBlank(message = "El número de documento es requerido")
    private String documentNumber;

    @NotBlank(message = "El email es requerido")
    private String email;
    private Team team;

    @NotBlank(message = "La contraseña es requerida")
    @Length(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    private Role role;
}
