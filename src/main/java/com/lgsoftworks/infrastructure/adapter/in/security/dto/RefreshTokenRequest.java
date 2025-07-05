package com.lgsoftworks.infrastructure.adapter.in.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {
    @NotNull(message = "Correo obligatorio")
    @Email(message = "Correo invalido")
    private String email;

    @NotNull(message = "Token obligatorio")
    private String token;
}