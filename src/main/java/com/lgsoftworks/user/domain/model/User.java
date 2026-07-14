package com.lgsoftworks.user.domain.model;

import com.lgsoftworks.user.domain.exception.InvalidUserDataException;
import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String cellphone;
    private String email;
    private String password;
    private String imageUrl;
    private Role role;

    protected User() {
    }

    public static User create(String firstName, String lastName, String cellphone,
                              String email, String encodedPassword) {
        User user = new User();
        user.firstName = requireNonBlank(firstName, "El nombre es requerido");
        user.lastName = requireNonBlank(lastName, "El apellido es requerido");
        user.fullName = (firstName + " " + lastName).trim();
        user.cellphone = requireNonBlank(cellphone, "El celular es requerido");
        user.email = requireNonBlank(email, "El email es requerido");
        user.password = encodedPassword;
        user.imageUrl = null;
        user.role = Role.USER;
        return user;
    }

    public static User rehydrate(Long id, String firstName, String lastName, String fullName,
                                 String cellphone, String email, String password,
                                 String imageUrl, Role role) {
        User user = new User();
        user.id = id;
        user.firstName = firstName;
        user.lastName = lastName;
        user.fullName = fullName;
        user.cellphone = cellphone;
        user.email = email;
        user.password = password;
        user.imageUrl = imageUrl;
        user.role = role;
        return user;
    }

    public void changeImageUrl(String newImageUrl) {
        if (newImageUrl == null || newImageUrl.isBlank()) {
            throw new InvalidUserDataException("La URL de imagen no puede estar vacía");
        }
        this.imageUrl = newImageUrl;
    }

    public void changePassword(String newEncodedPassword) {
        if (newEncodedPassword == null || newEncodedPassword.isBlank()) {
            throw new InvalidUserDataException("La contraseña no puede estar vacía");
        }
        this.password = newEncodedPassword;
    }

    public void promoteToRole(Role newRole) {
        if (newRole == null) {
            throw new InvalidUserDataException("El rol no puede ser nulo");
        }
        this.role = newRole;
    }

    private static String requireNonBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new InvalidUserDataException(message);
        }
        return value;
    }
}
