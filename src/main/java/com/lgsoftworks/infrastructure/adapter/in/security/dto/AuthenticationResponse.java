package com.lgsoftworks.infrastructure.adapter.in.security.dto;

import com.lgsoftworks.application.user.dto.response.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private UserDTO user;
}
