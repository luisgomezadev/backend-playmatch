package com.lgsoftworks.domain.dto.request;

import com.lgsoftworks.domain.enums.DocumentType;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.model.Field;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
public class AdminRequest {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String firstName;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastName;
    @NotBlank(message = "La ciudad no puede estar vacía")
    private String city;
    @NotNull(message = "La edad no puede ser nula")
    private Byte age;
    private DocumentType documentType;
    private String documentNumber;
    private String email;
    private Field field;
    @Length(min = 5, message = "Contraseña debe tener más de 5 caracteres")
    private String password;
    private Role role;
}
