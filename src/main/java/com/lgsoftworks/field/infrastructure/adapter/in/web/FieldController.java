package com.lgsoftworks.field.infrastructure.adapter.in.web;

import com.lgsoftworks.field.application.dto.request.FieldRequest;
import com.lgsoftworks.field.application.dto.response.FieldDTO;
import com.lgsoftworks.field.application.port.in.FieldUseCase;
import com.lgsoftworks.field.domain.exception.FieldByIdNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/field")
@RequiredArgsConstructor
@Validated
@Tag(name = "Canchas", description = "Operaciones relacionadas con las canchas de un complejo deportivo")
public class FieldController {

    private final FieldUseCase fieldUseCase;

    @Operation(summary = "Obtener las canchas de un complejo deportivo")
    @GetMapping("/venue/{venueId}")
    public ResponseEntity<List<FieldDTO>> getFieldsByVenueId(@PathVariable Long venueId) {
        return ResponseEntity.ok(fieldUseCase.findByVenueId(venueId));
    }

    @Operation(summary = "Obtener una cancha por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<FieldDTO> getFieldById(@PathVariable Long id) {
        return fieldUseCase.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new FieldByIdNotFoundException(id));
    }

    @Operation(summary = "Crear una nueva cancha")
    @PostMapping
    public ResponseEntity<FieldDTO> saveField(@Valid @RequestBody FieldRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fieldUseCase.save(request));
    }

    @Operation(summary = "Actualizar el precio por hora de una cancha")
    @PatchMapping("/{id}/price")
    public ResponseEntity<FieldDTO> updatePrice(
            @PathVariable Long id,
            @RequestParam @Positive(message = "El precio debe ser positivo") BigDecimal newHourlyRate) {
        return ResponseEntity.ok(fieldUseCase.updatePrice(id, newHourlyRate));
    }

    @Operation(summary = "Eliminar una cancha")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        fieldUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}