package com.lgsoftworks.infrastructure.security.service;

import com.lgsoftworks.application.mapper.UserModelMapper;
import com.lgsoftworks.infrastructure.rest.dto.request.AdminRequest;
import com.lgsoftworks.infrastructure.rest.dto.UserDTO;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.exception.InvalidCredentialsException;
import com.lgsoftworks.domain.exception.PasswordNotNullException;
import com.lgsoftworks.domain.exception.UserByEmailNotFoundException;
import com.lgsoftworks.domain.exception.UserWithEmailExistsException;
import com.lgsoftworks.domain.model.Admin;
import com.lgsoftworks.domain.port.in.AdminUseCase;
import com.lgsoftworks.domain.port.out.AdminRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.entity.AdminEntity;
import com.lgsoftworks.infrastructure.adapter.mapper.AdminDboMapper;
import com.lgsoftworks.infrastructure.security.dto.AuthenticationRequest;
import com.lgsoftworks.infrastructure.security.dto.AuthenticationResponse;
import com.lgsoftworks.infrastructure.security.dto.RefreshTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceAdmin {

    private final AdminRepositoryPort adminRepositoryPort;
    private final AdminUseCase adminUseCase;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserDTO register(AdminRequest admin) {

        if (adminRepositoryPort.findByEmail(admin.getEmail()).isPresent()) throw new UserWithEmailExistsException(admin.getEmail());

        if (admin.getPassword() == null) {
            throw new PasswordNotNullException();
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(Role.ADMIN);

        return adminUseCase.save(admin);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            throw new InvalidCredentialsException();
        }

        Admin admin = adminRepositoryPort.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserByEmailNotFoundException(request.getEmail()));

        AdminEntity adminEntity = AdminDboMapper.toDbo(admin);

        String jwtToken = jwtService.generateToken(adminEntity, "ADMIN");

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(UserModelMapper.toPersonSummary(admin))
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        Admin admin = adminRepositoryPort.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserByEmailNotFoundException(request.getEmail()));

        String newToken = jwtService.renewToken(request.getToken(), adminEntityToUserDetails(admin));

        return AuthenticationResponse.builder()
                .token(newToken)
                .user(UserModelMapper.toPersonSummary(admin))
                .build();
    }

    private UserDetails adminEntityToUserDetails(Admin admin) {
        return org.springframework.security.core.userdetails.User
                .withUsername(admin.getEmail())
                .password(admin.getPassword())
                .authorities("ADMIN")
                .build();
    }

}
