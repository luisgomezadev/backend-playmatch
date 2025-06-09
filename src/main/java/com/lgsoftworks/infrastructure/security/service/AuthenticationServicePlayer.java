package com.lgsoftworks.infrastructure.security.service;

import com.lgsoftworks.domain.dto.request.PlayerRequest;
import com.lgsoftworks.domain.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.exception.InvalidCredentialsException;
import com.lgsoftworks.domain.exception.PasswordNotNullException;
import com.lgsoftworks.domain.exception.PersonByEmailNotFoundException;
import com.lgsoftworks.domain.exception.PersonWithEmailExistsException;
import com.lgsoftworks.domain.model.Player;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.entity.PlayerEntity;
import com.lgsoftworks.infrastructure.adapter.mapper.PlayerDboMapper;
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
public class AuthenticationServicePlayer {

    private final PlayerRepositoryPort playerRepositoryPort;
    private final PlayerUseCase playerUseCase;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public PersonSummaryDTO register(PlayerRequest player) {

        if (playerRepositoryPort.findByEmail(player.getEmail()).isPresent()) throw new PersonWithEmailExistsException(player.getEmail());

        if (player.getPassword() == null) {
            throw new PasswordNotNullException();
        }

        player.setPassword(passwordEncoder.encode(player.getPassword()));
        player.setRole(Role.PLAYER);

        //PersonSummaryDTO saved = playerUseCase.save(player);

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
                .orElseThrow(() -> new PersonByEmailNotFoundException(request.getEmail()));

        PlayerEntity playerEntity = PlayerDboMapper.toDbo(player);

        String jwtToken = jwtService.generateToken(playerEntity);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
