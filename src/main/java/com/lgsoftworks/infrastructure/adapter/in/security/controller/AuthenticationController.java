package com.lgsoftworks.infrastructure.adapter.in.security.controller;

import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.infrastructure.adapter.in.security.dto.RefreshTokenRequest;
import com.lgsoftworks.infrastructure.security.service.AuthenticationService;
import com.lgsoftworks.infrastructure.adapter.in.security.dto.AuthenticationRequest;
import com.lgsoftworks.infrastructure.adapter.in.security.dto.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Autenticación", description = "Operaciones relacionadas con la autenticación")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Crear nuevo usuario")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Operation(summary = "Login")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @Operation(summary = "Refrescar Token")
    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }

}
