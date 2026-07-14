package com.lgsoftworks.user.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]+$", message = "El nombre solo puede contener letras")
    private String firstName;

    @NotBlank(message = "El apellido es requerido")
    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]+$", message = "El apellido solo puede contener letras")
    private String lastName;

    @NotBlank(message = "El número de celular es requerido")
    @Pattern(regexp = "^[0-9]{10}$", message = "El número de celular debe tener 10 dígitos numéricos")
    private String cellphone;

    @NotBlank(message = "El email es requerido")
    @Email(message = "Debe ingresar un correo válido")
    @Size(max = 100, message = "El correo no debe superar los 100 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Length(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

}