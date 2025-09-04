package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.port.in.CountReservationUseCase;
import com.lgsoftworks.domain.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.port.in.ReservationUseCase;
import com.lgsoftworks.application.dto.ReservationDTO;
import com.lgsoftworks.application.dto.request.ReservationRequest;
import com.lgsoftworks.infrastructure.adapter.in.rest.dto.MessageResponse;
import com.lgsoftworks.application.dto.ReservationAvailabilityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
@Validated
@Tag(name = "Reservaciones", description = "Operaciones relacionadas con las reservaciones")
public class ReservationController {

    private final ReservationUseCase reservationUseCase;
    private final CountReservationUseCase countReservationUseCase;
    private final ReservationAvailabilityUseCase reservationAvailabilityUseCase;

    @Operation(
            summary = "Obtener todas las reservas",
            description = "Devuelve una lista completa de todas las reservas registradas en el sistema."
    )
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        return ResponseEntity.ok(reservationUseCase.findAll());
    }

    @Operation(
            summary = "Filtrar reservas",
            description = "Devuelve una lista paginada de reservas que coinciden con los filtros opcionales: fecha, estado, ID del usuario y ID del campo.",
            parameters = {
                    @Parameter(name = "date", description = "Fecha de la reserva (formato: yyyy-MM-dd)", example = "2025-07-17"),
                    @Parameter(name = "status", description = "Estado de la reserva (por ejemplo: ACTIVE, CANCELED, FINISHED)", example = "ACTIVE"),
                    @Parameter(name = "userId", description = "ID del usuario que realizó la reserva", example = "1"),
                    @Parameter(name = "fieldId", description = "ID del campo reservado", example = "2"),
                    @Parameter(name = "page", description = "Número de página para la paginación (por defecto: 0)", example = "0"),
                    @Parameter(name = "size", description = "Tamaño de página (por defecto: 6)", example = "6")
            }
    )
    @GetMapping("/filter")
    public ResponseEntity<Page<ReservationDTO>> getFilteredReservations(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date,
            @RequestParam(value = "status", required = false) StatusReservation status,
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "fieldId", required = false) Long fieldId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
            ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(reservationUseCase.findByFilters(date, status, userId, fieldId, pageable));
    }

    @Operation(
            summary = "Obtener reservas por ID de campo",
            description = "Devuelve una lista de reservas asociadas a un campo específico, identificado por su ID.",
            parameters = {
                    @Parameter(
                            name = "fieldId",
                            description = "ID del campo para el que se desean obtener las reservas",
                            required = true,
                            example = "1"
                    )
            }
    )
    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByFieldId(@PathVariable Long fieldId) {
        return ResponseEntity.ok(reservationUseCase.findByFieldId(fieldId));
    }

    @Operation(
            summary = "Obtener reservas por cancha y estado",
            description = "Devuelve una lista de reservas que pertenecen a una cancha específica y que tienen un estado determinado.",
            parameters = {
                    @Parameter(
                            name = "fieldId",
                            description = "ID de la cancha",
                            required = true,
                            example = "1"
                    ),
                    @Parameter(
                            name = "status",
                            description = "Estado de la reserva (por ejemplo: ACTIVE, CANCELED, FINISHED)",
                            required = true,
                            schema = @Schema(implementation = StatusReservation.class)
                    )
            }
    )
    @GetMapping("/field/{fieldId}/status/{status}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByFieldIdAndStatus(@PathVariable Long fieldId,
                                                                                  @PathVariable StatusReservation status) {
        return ResponseEntity.ok(reservationUseCase.findByFieldIdAndStatus(fieldId, status));
    }

    @Operation(
            summary = "Obtener reservas por usuario",
            description = "Devuelve una lista de reservas asociadas a un usuario específico.",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "ID del usuario",
                            required = true,
                            example = "1"
                    )
            }
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationUseCase.findByUserId(userId));
    }

    @Operation(
            summary = "Crear una nueva reserva",
            description = "Guarda una nueva reserva en el sistema usando los datos enviados en el cuerpo de la petición."
    )
    @PostMapping
    public ResponseEntity<ReservationDTO> saveReservation(@Valid @RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationUseCase.save(reservationRequest));
    }

    @Operation(
            summary = "Cancelar una reserva",
            description = "Actualiza el estado de una reserva a CANCELADO usando su ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID de la reserva que se desea cancelar",
                            required = true,
                            example = "12"
                    )
            }
    )
    @PutMapping("/{id}/status/canceled")
    public ResponseEntity<MessageResponse> canceledStatus(@PathVariable Long id) {
        reservationUseCase.updateStatus(id, StatusReservation.CANCELED);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse("Se ha actualizado el estado de la reserva"));
    }

    @Operation(
            summary = "Finalizar una reserva",
            description = "Actualiza el estado de una reserva a FINALIZADO usando su ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID de la reserva que se desea finalizar",
                            required = true,
                            example = "12"
                    )
            }
    )
    @PutMapping("/{id}/status/finalize")
    public ResponseEntity<MessageResponse> finalizeStatus(@PathVariable Long id) {
        reservationUseCase.updateStatus(id, StatusReservation.FINISHED);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse("Se ha actualizado el estado de la reserva"));
    }

    @Operation(
            summary = "Obtener cantidad de reservas activas por usuario",
            description = "Devuelve la cantidad total de reservas con estado ACTIVO asociadas al usuario especificado.",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "ID del usuario",
                            required = true,
                            example = "5"
                    )
            }
    )
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<Long> getCountActiveByUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countReservationUseCase.countReservationsByUserAndStatus(userId, StatusReservation.ACTIVE));
    }

    @Operation(
            summary = "Obtener cantidad de reservas activas por campo",
            description = "Devuelve el número total de reservas con estado ACTIVO asociadas al campo especificado.",
            parameters = {
                    @Parameter(
                            name = "fieldId",
                            description = "ID del campo",
                            required = true,
                            example = "3"
                    )
            }
    )
    @GetMapping("/field/{fieldId}/active")
    public ResponseEntity<Long> getCountActiveByField(@PathVariable Long fieldId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countReservationUseCase.countReservationsByFieldAndStatus(fieldId, StatusReservation.ACTIVE));
    }

    @Operation(
            summary = "Obtener cantidad de reservas canceladas por campo",
            description = "Devuelve el número total de reservas con estado CANCELED asociadas al campo especificado.",
            parameters = {
                    @Parameter(
                            name = "fieldId",
                            description = "ID del campo",
                            required = true,
                            example = "3"
                    )
            }
    )
    @GetMapping("/field/{fieldId}/canceled")
    public ResponseEntity<Long> getCountCanceledByField(@PathVariable Long fieldId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countReservationUseCase
                        .countReservationsByFieldAndStatus(fieldId, StatusReservation.CANCELED)
                );
    }

    @Operation(
            summary = "Obtener cantidad de reservas finalizadas por campo",
            description = "Devuelve el número total de reservas con estado FINISHED asociadas al campo especificado.",
            parameters = {
                    @Parameter(
                            name = "fieldId",
                            description = "ID del campo",
                            required = true,
                            example = "3"
                    )
            }
    )
    @GetMapping("/field/{fieldId}/finished")
    public ResponseEntity<Long> getCountFinishedByField(@PathVariable Long fieldId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countReservationUseCase
                        .countReservationsByFieldAndStatus(fieldId, StatusReservation.FINISHED)
                );
    }

    @Operation(
            summary = "Verificar disponibilidad de reserva",
            description = "Recibe los datos de la reserva y verifica la disponibilidad para esa fecha y campo y devuelve la reserva."
    )
    @PostMapping("/availability")
    public ResponseEntity<Optional<ReservationAvailabilityDTO>> getReservationAvailability(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationAvailabilityUseCase
                        .reservationAvailability(reservationRequest)
                );
    }

    @GetMapping("/latest/{fieldId}")
    public ResponseEntity<List<ReservationDTO>> getLastThreeReservationsByField(@PathVariable Long fieldId) {
        return ResponseEntity.ok(reservationUseCase.findLastThreeReservations(fieldId));
    }

}