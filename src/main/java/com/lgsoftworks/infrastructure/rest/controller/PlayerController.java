package com.lgsoftworks.infrastructure.rest.controller;

import com.lgsoftworks.domain.dto.PlayerDTO;
import com.lgsoftworks.domain.dto.request.PlayerRequest;
import com.lgsoftworks.domain.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
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

    @PostMapping
    public ResponseEntity<PersonSummaryDTO> savePlayer(@RequestBody PlayerRequest playerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerUseCase.save(playerRequest));
    }

    @PostMapping("/list")
    public ResponseEntity<List<PersonSummaryDTO>> savePlayers(@RequestBody List<PlayerRequest> playerRequests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerUseCase.saveAll(playerRequests));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonSummaryDTO> updatePlayer(@PathVariable Long id, @RequestBody PlayerRequest playerRequest){
        return ResponseEntity.ok(playerUseCase.update(playerRequest, id));
    }

}
