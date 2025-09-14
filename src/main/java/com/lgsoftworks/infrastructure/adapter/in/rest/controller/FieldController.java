package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.domain.field.port.in.FieldUseCase;
import com.lgsoftworks.application.field.dto.response.FieldDTO;
import com.lgsoftworks.infrastructure.adapter.in.rest.dto.MessageResponse;
import com.lgsoftworks.application.field.dto.request.FieldRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/field")
@RequiredArgsConstructor
@Validated
@Tag(name = "Canchas", description = "Operaciones relacionadas con las canchas")
public class FieldController {

    private final FieldUseCase fieldUseCase;

    @Operation(summary = "Obtener una cancha por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cancha encontrada"),
            @ApiResponse(responseCode = "404", description = "Cancha no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<FieldDTO>> getFieldById(@PathVariable Long id) {
        return ResponseEntity.ok(fieldUseCase.findById(id));
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

}
