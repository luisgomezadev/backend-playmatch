package com.lgsoftworks.infrastructure.security.dto;

import com.lgsoftworks.infrastructure.rest.dto.summary.PersonSummaryDTO;
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
    private PersonSummaryDTO user;
}
