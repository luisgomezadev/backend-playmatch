package com.lgsoftworks.infrastructure.rest.controller;

import com.lgsoftworks.application.mapper.AdminModelMapper;
import com.lgsoftworks.application.mapper.PlayerModelMapper;
import com.lgsoftworks.domain.dto.request.AdminRequest;
import com.lgsoftworks.domain.dto.request.PlayerRequest;
import com.lgsoftworks.domain.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.domain.model.Admin;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.infrastructure.security.admin.AuthenticationServiceAdmin;
import com.lgsoftworks.infrastructure.security.player.AuthenticationService;
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
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final AuthenticationServiceAdmin serviceAdmin;

    @PostMapping("/player/register")
    public ResponseEntity<PersonSummaryDTO> registerPlayer(@Valid @RequestBody PlayerRequest request) {//@Validated
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/admin/register")
    public ResponseEntity<PersonSummaryDTO> registerAdmin(@Valid @RequestBody AdminRequest request) {//@Validated
        return ResponseEntity.ok(serviceAdmin.register(request));
    }

    @PostMapping("/authenticate/player")
    public ResponseEntity<AuthenticationResponse> authenticatePlayer(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/authenticate/admin")
    public ResponseEntity<AuthenticationResponse> authenticateAdmin(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(serviceAdmin.authenticate(request));
    }

}
