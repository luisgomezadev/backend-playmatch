package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.venue.dto.request.VenueFilter;
import com.lgsoftworks.application.venue.dto.request.VenueRequest;
import com.lgsoftworks.application.venue.dto.response.VenueDTO;
import com.lgsoftworks.domain.venue.port.in.VenueUseCase;
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

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/venue")
@RequiredArgsConstructor
@Validated
@Tag(name = "Complejos", description = "Operaciones relacionadas con los complejos deportivos")
public class VenueController {

    private final VenueUseCase venueUseCase;

    @Operation(summary = "Obtener los complejos deportivos paginados")
    @GetMapping
    public ResponseEntity<PageResponse<VenueDTO>> getVenues(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        VenueFilter filter = new VenueFilter(name, city);
        return ResponseEntity.ok(venueUseCase.searchVenues(
                filter, PageRequest.of(page, size)
        ));
    }

    @Operation(summary = "Obtener un complejo por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complejo encontrado"),
            @ApiResponse(responseCode = "404", description = "Complejo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<VenueDTO>> getVenueById(@PathVariable Long id) {
        return ResponseEntity.ok(venueUseCase.findById(id));
    }

    @Operation(summary = "Obtener un complejo por ID del admin")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complejo encontrado"),
            @ApiResponse(responseCode = "404", description = "Complejo no encontrado")
    })
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<Optional<VenueDTO>> getVenueByAdminId(@PathVariable Long adminId) {
        return ResponseEntity.ok(venueUseCase.findByAdminId(adminId));
    }

    @Operation(summary = "Obtener un complejo por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complejo encontrado"),
            @ApiResponse(responseCode = "404", description = "Complejo no encontrado")
    })
    @GetMapping("/code/{code}")
    public ResponseEntity<Optional<VenueDTO>> getVenueByCode(@PathVariable String code) {
        return ResponseEntity.ok(venueUseCase.findByCode(code));
    }

    @Operation(summary = "Crear un nuevo complejo deportivo")
    @PostMapping
    public ResponseEntity<VenueDTO> saveVenue(@Valid @RequestBody VenueRequest venueRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(venueUseCase.save(venueRequest));
    }

}
