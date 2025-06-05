package com.lgsoftworks.infrastructure.rest.controller;

import com.lgsoftworks.domain.dto.PlayerDTO;
import com.lgsoftworks.domain.dto.request.PlayerRequest;
import com.lgsoftworks.domain.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/person/player")
@RequiredArgsConstructor
@Tag(name = "Jugadores", description = "Operaciones CRUD para jugadores")
public class PlayerController {

    private final PlayerUseCase playerUseCase;

    @GetMapping
    @Operation(summary = "Obtener todos los jugadores")
    public ResponseEntity<List<PlayerDTO>> getPlayers() {
        return ResponseEntity.ok(playerUseCase.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener jugador por ID")
    public ResponseEntity<Optional<PlayerDTO>> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerUseCase.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo jugador")
    public ResponseEntity<PersonSummaryDTO> savePlayer(@RequestBody PlayerRequest playerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerUseCase.save(playerRequest));
    }

    @PostMapping("/list")
    @Operation(summary = "Eliminar jugador por ID")
    public ResponseEntity<List<PersonSummaryDTO>> savePlayers(@RequestBody List<PlayerRequest> playerRequests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerUseCase.saveAll(playerRequests));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar jugador por ID")
    public ResponseEntity<PersonSummaryDTO> updatePlayer(@PathVariable Long id, @RequestBody PlayerRequest playerRequest){
        return ResponseEntity.ok(playerUseCase.update(playerRequest, id));
    }

}
