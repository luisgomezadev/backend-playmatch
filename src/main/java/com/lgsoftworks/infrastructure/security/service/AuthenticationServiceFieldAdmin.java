package com.lgsoftworks.infrastructure.security.service;

import com.lgsoftworks.application.mapper.UserModelMapper;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.exception.InvalidCredentialsException;
import com.lgsoftworks.domain.exception.PasswordNotNullException;
import com.lgsoftworks.domain.exception.UserByEmailNotFoundException;
import com.lgsoftworks.domain.exception.UserWithEmailExistsException;
import com.lgsoftworks.domain.model.FieldAdmin;
import com.lgsoftworks.domain.port.in.FieldAdminUseCase;
import com.lgsoftworks.domain.port.out.FieldAdminRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldAdminEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.FieldAdminDboMapper;
import com.lgsoftworks.infrastructure.adapter.in.security.dto.AuthenticationRequest;
import com.lgsoftworks.infrastructure.adapter.in.security.dto.AuthenticationResponse;
import com.lgsoftworks.infrastructure.adapter.in.security.dto.RefreshTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceFieldAdmin {

    private final FieldAdminRepositoryPort fieldAdminRepositoryPort;
    private final FieldAdminUseCase fieldAdminUseCase;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserDTO register(UserRequest admin) {

        if (fieldAdminRepositoryPort.findByEmail(admin.getEmail()).isPresent()) throw new UserWithEmailExistsException(admin.getEmail());

        if (admin.getPassword() == null) {
            throw new PasswordNotNullException();
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(Role.FIELD_ADMIN);

        return fieldAdminUseCase.save(admin);
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

        FieldAdmin fieldAdmin = fieldAdminRepositoryPort.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserByEmailNotFoundException(request.getEmail()));

        FieldAdminEntity fieldAdminEntity = FieldAdminDboMapper.toDbo(fieldAdmin);

        String jwtToken = jwtService.generateToken(fieldAdminEntity, "FIELD_ADMIN");

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(UserModelMapper.toUserDTO(fieldAdmin))
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        FieldAdmin fieldAdmin = fieldAdminRepositoryPort.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserByEmailNotFoundException(request.getEmail()));

        String newToken = jwtService.renewToken(request.getToken(), adminEntityToUserDetails(fieldAdmin));

        return AuthenticationResponse.builder()
                .token(newToken)
                .user(UserModelMapper.toUserDTO(fieldAdmin))
                .build();
    }

    private UserDetails adminEntityToUserDetails(FieldAdmin fieldAdmin) {
        return org.springframework.security.core.userdetails.User
                .withUsername(fieldAdmin.getEmail())
                .password(fieldAdmin.getPassword())
                .authorities("FIELD_ADMIN")
                .build();
    }

}
