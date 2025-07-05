package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.application.dto.FieldAdminDTO;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.domain.port.in.FieldAdminUseCase;
import com.lgsoftworks.domain.port.in.UploadAdminImageUseCase;
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
@RequestMapping("/api/v1/user/admin")
@RequiredArgsConstructor
@Validated
public class FieldAdminController {

    private final FieldAdminUseCase fieldAdminUseCase;
    private final UploadAdminImageUseCase uploadAdminImageUseCase;

    @GetMapping
    public ResponseEntity<List<FieldAdminDTO>> getAdmins() {
        return ResponseEntity.ok(fieldAdminUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FieldAdminDTO>> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(fieldAdminUseCase.findById(id));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateAdmin(@Valid @RequestBody UserRequest fieldAdminRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fieldAdminUseCase.update(fieldAdminRequest));
    }

    @GetMapping("/by-email")
    public ResponseEntity<Optional<FieldAdminDTO>> getAdminByEmail(@RequestParam String email) {
        return ResponseEntity.ok(fieldAdminUseCase.findByEmail(email));
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<UserDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        UserDTO updatedUser = uploadAdminImageUseCase.uploadAdminImage(id, file);
        return ResponseEntity.ok(updatedUser);
    }

}
