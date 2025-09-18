package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.application.user.dto.response.UserDTO;
import com.lgsoftworks.domain.user.port.in.UploadUserImageUseCase;
import com.lgsoftworks.domain.user.port.in.UserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Validated
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class UserController {

    private final UserUseCase userUseCase;
    private final UploadUserImageUseCase uploadUserImageUseCase;

    @Operation(summary = "Obtener un jugador por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jugador encontrado"),
            @ApiResponse(responseCode = "404", description = "Jugador no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(userUseCase.findById(id));
    }

    @GetMapping("/by-email")
    public ResponseEntity<Optional<UserDTO>> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userUseCase.findByEmail(email));
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<UserDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        UserDTO updatedUser = uploadUserImageUseCase.uploadUserImage(id, file);
        return ResponseEntity.ok(updatedUser);
    }

}
