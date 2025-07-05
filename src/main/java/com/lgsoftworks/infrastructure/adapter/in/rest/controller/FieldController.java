package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.domain.port.in.FieldUseCase;
import com.lgsoftworks.application.dto.FieldDTO;
import com.lgsoftworks.domain.port.in.UploadFieldImageUseCase;
import com.lgsoftworks.infrastructure.adapter.in.rest.dto.MessageResponse;
import com.lgsoftworks.application.dto.request.FieldRequest;
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
@RequestMapping("/api/v1/field")
@RequiredArgsConstructor
@Validated
public class FieldController {

    private final FieldUseCase fieldUseCase;
    private final UploadFieldImageUseCase uploadFieldImageUseCase;

    @GetMapping
    public ResponseEntity<List<FieldDTO>> getFields() {
        return ResponseEntity.ok().body(fieldUseCase.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<FieldDTO>> getFieldsActive() {
        return ResponseEntity.ok().body(fieldUseCase.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FieldDTO>> getFieldById(@PathVariable Long id) {
        return ResponseEntity.ok(fieldUseCase.findById(id));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Optional<FieldDTO>> getFieldByAdminId(@PathVariable Long id) {
        return ResponseEntity.ok(fieldUseCase.findByAdminId(id));
    }

    @PostMapping
    public ResponseEntity<FieldDTO> saveField(@Valid @RequestBody FieldRequest fieldRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fieldUseCase.save(fieldRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteTeamById(@PathVariable Long id){
        fieldUseCase.deleteById(id);
        return ResponseEntity.ok().body(new MessageResponse("Campo eliminado exitosamente!"));
    }

    @PutMapping
    public ResponseEntity<FieldDTO> updateField(@Valid @RequestBody FieldRequest fieldRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(fieldUseCase.update(fieldRequest));
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<FieldDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        FieldDTO updatedField = uploadFieldImageUseCase.uploadFieldImage(id, file);
        return ResponseEntity.ok(updatedField);
    }
}
