package com.lgsoftworks.infrastructure.adapter.in.security.controller;

import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.infrastructure.adapter.in.security.dto.RefreshTokenRequest;
import com.lgsoftworks.infrastructure.security.service.AuthenticationServiceFieldAdmin;
import com.lgsoftworks.infrastructure.security.service.AuthenticationServicePlayer;
import com.lgsoftworks.infrastructure.adapter.in.security.dto.AuthenticationRequest;
import com.lgsoftworks.infrastructure.adapter.in.security.dto.AuthenticationResponse;
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
public class AuthenticationController {

    private final AuthenticationServicePlayer servicePlayer;
    private final AuthenticationServiceFieldAdmin serviceAdmin;

    @PostMapping("/player/register")
    public ResponseEntity<UserDTO> registerPlayer(@RequestBody @Valid UserRequest request) {//@Validated
        return ResponseEntity.ok(servicePlayer.register(request));
    }

    @PostMapping("/admin/register")
    public ResponseEntity<UserDTO> registerAdmin(@RequestBody @Valid UserRequest request) {//@Validated
        return ResponseEntity.ok(serviceAdmin.register(request));
    }

    @PostMapping("/authenticate/player")
    public ResponseEntity<AuthenticationResponse> authenticatePlayer(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(servicePlayer.authenticate(request));
    }

    @PostMapping("/authenticate/admin")
    public ResponseEntity<AuthenticationResponse> authenticateAdmin(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(serviceAdmin.authenticate(request));
    }

    @PostMapping("/refresh/player")
    public ResponseEntity<AuthenticationResponse> refreshPlayerToken(@RequestBody @Valid RefreshTokenRequest request) {
        return ResponseEntity.ok(servicePlayer.refreshToken(request));
    }

    @PostMapping("/refresh/admin")
    public ResponseEntity<AuthenticationResponse> refreshAdminToken(@RequestBody @Valid RefreshTokenRequest request) {
        return ResponseEntity.ok(serviceAdmin.refreshToken(request));
    }

}
