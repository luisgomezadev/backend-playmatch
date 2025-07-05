package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.application.dto.PlayerDTO;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
import com.lgsoftworks.application.dto.summary.PlayerSummaryDTO;
import com.lgsoftworks.domain.port.in.UploadAdminImageUseCase;
import com.lgsoftworks.domain.port.in.UploadPlayerImageUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/player")
@RequiredArgsConstructor
@Validated
public class PlayerController {

    private final PlayerUseCase playerUseCase;
    private final UploadPlayerImageUseCase uploadPlayerImageUseCase;

    @GetMapping
    public ResponseEntity<List<PlayerSummaryDTO>> getPlayers() {
        return ResponseEntity.ok(playerUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PlayerSummaryDTO>> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerUseCase.findById(id));
    }

    @PostMapping("/list")
    public ResponseEntity<List<UserDTO>> savePlayers(@RequestBody List<UserRequest> playerRequests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerUseCase.saveAll(playerRequests));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updatePlayer(@Valid @RequestBody UserRequest playerRequest){
        return ResponseEntity.ok(playerUseCase.update(playerRequest));
    }

    @GetMapping("/by-email")
    public ResponseEntity<Optional<PlayerDTO>> getPlayerByEmail(@RequestParam String email) {
        return ResponseEntity.ok(playerUseCase.findByEmail(email));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<PlayerSummaryDTO>> getPlayersByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(playerUseCase.findAllByTeamId(teamId));
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<UserDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        UserDTO updatedUser = uploadPlayerImageUseCase.uploadPlayerImage(id, file);
        return ResponseEntity.ok(updatedUser);
    }

}
