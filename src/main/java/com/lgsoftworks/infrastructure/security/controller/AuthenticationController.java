package com.lgsoftworks.infrastructure.security.controller;

import com.lgsoftworks.infrastructure.rest.dto.request.AdminRequest;
import com.lgsoftworks.infrastructure.rest.dto.request.PlayerRequest;
import com.lgsoftworks.infrastructure.rest.dto.UserDTO;
import com.lgsoftworks.infrastructure.security.service.AuthenticationServiceAdmin;
import com.lgsoftworks.infrastructure.security.service.AuthenticationServicePlayer;
import com.lgsoftworks.infrastructure.security.dto.AuthenticationRequest;
import com.lgsoftworks.infrastructure.security.dto.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServicePlayer servicePlayer;
    private final AuthenticationServiceAdmin serviceAdmin;

    @PostMapping("/player/register")
    public ResponseEntity<UserDTO> registerPlayer(@RequestBody @Valid PlayerRequest request) {//@Validated
        return ResponseEntity.ok(servicePlayer.register(request));
    }

    @PostMapping("/admin/register")
    public ResponseEntity<UserDTO> registerAdmin(@RequestBody @Valid AdminRequest request) {//@Validated
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

}
