package com.lgsoftworks.infrastructure.rest.controller;

import com.lgsoftworks.infrastructure.rest.dto.AdminDTO;
import com.lgsoftworks.infrastructure.rest.dto.PlayerDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.PlayerRequest;
import com.lgsoftworks.infrastructure.rest.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/person/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerUseCase playerUseCase;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getPlayers() {
        return ResponseEntity.ok(playerUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PlayerDTO>> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerUseCase.findById(id));
    }

    @PostMapping("/list")
    public ResponseEntity<List<PersonSummaryDTO>> savePlayers(@RequestBody List<PlayerRequest> playerRequests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerUseCase.saveAll(playerRequests));
    }

    @PutMapping
    public ResponseEntity<PersonSummaryDTO> updatePlayer(@RequestBody @Valid PlayerRequest playerRequest){
        return ResponseEntity.ok(playerUseCase.update(playerRequest));
    }

    @GetMapping("/by-email")
    public ResponseEntity<Optional<PlayerDTO>> getPlayerByEmail(@RequestParam String email) {
        return ResponseEntity.ok(playerUseCase.findByEmail(email));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<PlayerDTO>> getPlayersByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(playerUseCase.findAllByTeamId(teamId));
    }

}
