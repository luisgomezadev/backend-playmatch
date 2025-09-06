package com.lgsoftworks.application.dto.request;

import com.lgsoftworks.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter {
    private String name;
    private String city;
    private Role role;
}
