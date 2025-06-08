package com.lgsoftworks.infrastructure.security.admin;

import com.lgsoftworks.application.mapper.AdminModelMapper;
import com.lgsoftworks.application.mapper.PersonModelMapper;
import com.lgsoftworks.domain.dto.AdminDTO;
import com.lgsoftworks.domain.dto.request.AdminRequest;
import com.lgsoftworks.domain.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.exception.InvalidCredentialsException;
import com.lgsoftworks.domain.exception.PasswordNotNullException;
import com.lgsoftworks.domain.exception.PersonByEmailNotFoundException;
import com.lgsoftworks.domain.exception.PersonWithEmailExistsException;
import com.lgsoftworks.domain.model.Admin;
import com.lgsoftworks.domain.port.in.AdminUseCase;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
import com.lgsoftworks.domain.port.out.AdminRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.entity.AdminEntity;
import com.lgsoftworks.infrastructure.adapter.mapper.AdminDboMapper;
import com.lgsoftworks.infrastructure.security.JwtService;
import com.lgsoftworks.infrastructure.security.dto.AuthenticationRequest;
import com.lgsoftworks.infrastructure.security.dto.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    public PersonSummaryDTO register(AdminRequest admin) {

        if (adminRepositoryPort.findByEmail(admin.getEmail()).isPresent()) throw new PersonWithEmailExistsException(admin.getEmail());

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
                .orElseThrow(() -> new PersonByEmailNotFoundException(request.getEmail()));

        AdminEntity adminEntity = AdminDboMapper.toDbo(admin);

        String jwtToken = jwtService.generateToken(adminEntity);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
