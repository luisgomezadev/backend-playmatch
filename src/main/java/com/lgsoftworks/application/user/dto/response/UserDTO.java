package com.lgsoftworks.application.user.dto.response;

import com.lgsoftworks.domain.user.enums.Role;
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
    private String city;
    private String cellphone;
    private String email;
    private String imageUrl;
    private Role role;
}
