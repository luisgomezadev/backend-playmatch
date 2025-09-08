package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.user.dto.request.UserFilter;
import com.lgsoftworks.application.user.dto.request.UserRequest;
import com.lgsoftworks.application.user.dto.response.UserDTO;
import com.lgsoftworks.domain.user.enums.Role;
import com.lgsoftworks.domain.user.port.in.UploadUserImageUseCase;
import com.lgsoftworks.domain.user.port.in.UserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    @Operation(summary = "Obtener todos los usuarios por ROL")
    @ApiResponse(responseCode = "200", description = "Lista de jugadores encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)))
    @GetMapping
    public ResponseEntity<PageResponse<UserDTO>> getUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(required = false) Role role) {
        UserFilter filter = new UserFilter(name, city, role);
        return ResponseEntity.ok(userUseCase.searchUsers(
                filter, PageRequest.of(page, size)
        ));
    }

    @Operation(summary = "Obtener un jugador por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jugador encontrado"),
            @ApiResponse(responseCode = "404", description = "Jugador no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(userUseCase.findById(id));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updatePlayer(@Valid @RequestBody UserRequest playerRequest){
        return ResponseEntity.ok(userUseCase.update(playerRequest));
    }

    @GetMapping("/by-email")
    public ResponseEntity<Optional<UserDTO>> getPlayerByEmail(@RequestParam String email) {
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
