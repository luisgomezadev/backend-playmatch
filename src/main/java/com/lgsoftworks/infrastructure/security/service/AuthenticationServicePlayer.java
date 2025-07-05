package com.lgsoftworks.infrastructure.security.service;

import com.lgsoftworks.application.mapper.UserModelMapper;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.exception.InvalidCredentialsException;
import com.lgsoftworks.domain.exception.PasswordNotNullException;
import com.lgsoftworks.domain.exception.UserByEmailNotFoundException;
import com.lgsoftworks.domain.exception.UserWithEmailExistsException;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.PlayerEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.PlayerDboMapper;
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
public class AuthenticationServicePlayer {

    private final PlayerRepositoryPort playerRepositoryPort;
    private final PlayerUseCase playerUseCase;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserDTO register(UserRequest player) {

        if (playerRepositoryPort.findByEmail(player.getEmail()).isPresent()) throw new UserWithEmailExistsException(player.getEmail());

        if (player.getPassword() == null) {
            throw new PasswordNotNullException();
        }

        player.setPassword(passwordEncoder.encode(player.getPassword()));
        player.setRole(Role.PLAYER);

        return playerUseCase.save(player);
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

        Player player = playerRepositoryPort.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserByEmailNotFoundException(request.getEmail()));

        PlayerEntity playerEntity = PlayerDboMapper.toDbo(player);

        String jwtToken = jwtService.generateToken(playerEntity, "PLAYER");

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(UserModelMapper.toUserDTO(player))
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        Player player = playerRepositoryPort.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserByEmailNotFoundException(request.getEmail()));

        String newToken = jwtService.renewToken(request.getToken(), playerEntityToUserDetails(player));

        return AuthenticationResponse.builder()
                .token(newToken)
                .user(UserModelMapper.toUserDTO(player))
                .build();
    }

    private UserDetails playerEntityToUserDetails(Player player) {
        return org.springframework.security.core.userdetails.User
                .withUsername(player.getEmail())
                .password(player.getPassword())
                .authorities("PLAYER")
                .build();
    }

}
