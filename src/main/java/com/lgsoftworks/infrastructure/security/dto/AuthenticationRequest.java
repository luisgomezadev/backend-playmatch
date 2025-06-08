package com.lgsoftworks.infrastructure.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotNull(message = "Correo obligatorio")
    @Email(message = "Correo invalido")
    private String email;
    @NotNull(message = "Contraseña obligatoria")
    @Length(min = 5, message = "Contraseña debe tener más de 5 caracteres")
    private String password;
}