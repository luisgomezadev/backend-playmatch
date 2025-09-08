package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.field.dto.request.FieldFilter;
import com.lgsoftworks.domain.field.port.in.FieldUseCase;
import com.lgsoftworks.application.field.dto.response.FieldDTO;
import com.lgsoftworks.domain.field.port.in.UploadFieldImageUseCase;
import com.lgsoftworks.infrastructure.adapter.in.rest.dto.MessageResponse;
import com.lgsoftworks.application.field.dto.request.FieldRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/field")
@RequiredArgsConstructor
@Validated
@Tag(name = "Canchas", description = "Operaciones relacionadas con las canchas")
public class FieldController {

    private final FieldUseCase fieldUseCase;
    private final UploadFieldImageUseCase uploadFieldImageUseCase;

    /*@Operation(summary = "Obtener todos los canchas")
    @ApiResponse(responseCode = "200", description = "Lista de canchas encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FieldDTO.class)))
    @GetMapping
    public ResponseEntity<List<FieldDTO>> getFields() {
        return ResponseEntity.ok().body(fieldUseCase.findAll());
    }*/

    @GetMapping
    public ResponseEntity<PageResponse<FieldDTO>> getFields(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        FieldFilter filter = new FieldFilter(name, city, minPrice, maxPrice);
        return ResponseEntity.ok(fieldUseCase.searchFields(
                filter, PageRequest.of(page, size)
        ));
    }

    @Operation(summary = "Obtener todas los canchas activas")
    @GetMapping("/active")
    public ResponseEntity<List<FieldDTO>> getFieldsActive() {
        return ResponseEntity.ok().body(fieldUseCase.findAllActive());
    }

    @Operation(summary = "Obtener una cancha por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cancha encontrada"),
            @ApiResponse(responseCode = "404", description = "Cancha no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<FieldDTO>> getFieldById(@PathVariable Long id) {
        return ResponseEntity.ok(fieldUseCase.findById(id));
    }

    @Operation(summary = "Obtener una cancha por el ID del administrador")
    @GetMapping("/admin/{id}")
    public ResponseEntity<Optional<FieldDTO>> getFieldByAdminId(@PathVariable Long id) {
        return ResponseEntity.ok(fieldUseCase.findByAdminId(id));
    }

    @Operation(summary = "Crear una nueva cancha")
    @PostMapping
    public ResponseEntity<FieldDTO> saveField(@Valid @RequestBody FieldRequest fieldRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fieldUseCase.save(fieldRequest));
    }

    @Operation(summary = "Eliminar una cancha por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFieldById(@PathVariable Long id){
        fieldUseCase.deleteById(id);
        return ResponseEntity.ok().body(new MessageResponse("Campo eliminado exitosamente!"));
    }

    @Operation(summary = "Actualizar una cancha existente")
    @PutMapping
    public ResponseEntity<FieldDTO> updateField(@Valid @RequestBody FieldRequest fieldRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(fieldUseCase.update(fieldRequest));
    }

    @Operation(summary = "Subir imagen para una cancha")
    @PostMapping("/{id}/upload-image")
    public ResponseEntity<FieldDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        FieldDTO updatedField = uploadFieldImageUseCase.uploadFieldImage(id, file);
        return ResponseEntity.ok(updatedField);
    }
}
