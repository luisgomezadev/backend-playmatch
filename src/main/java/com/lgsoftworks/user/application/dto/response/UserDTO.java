package com.lgsoftworks.user.application.dto.response;

import com.lgsoftworks.user.domain.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String cellphone;
    private String email;
    private String imageUrl;
    private Role role;
}
