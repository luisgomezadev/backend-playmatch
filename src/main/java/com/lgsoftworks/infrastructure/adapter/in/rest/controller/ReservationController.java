package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.application.reservation.dto.response.TimeSlot;
import com.lgsoftworks.domain.common.enums.Status;
import com.lgsoftworks.domain.reservation.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.reservation.port.in.ReservationUseCase;
import com.lgsoftworks.application.reservation.dto.response.ReservationDTO;
import com.lgsoftworks.application.reservation.dto.request.ReservationRequest;
import com.lgsoftworks.infrastructure.adapter.in.rest.dto.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final ReservationAvailabilityUseCase reservationAvailabilityUseCase;

    @Operation(
            summary = "Obtener reservas por ID de la cancha",
            description = "Devuelve una lista de reservas asociadas a una cancha específica, identificada por su ID.",
            parameters = {
                    @Parameter(
                            name = "fieldId",
                            description = "ID de la cancha para la que se desean obtener las reservas",
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
            summary = "Obtener reservas por ID del complejo",
            description = "Devuelve una lista de reservas asociadas a un complejo específico, identificado por su ID.",
            parameters = {
                    @Parameter(
                            name = "venueId",
                            description = "ID del específico para el que se desean obtener las reservas",
                            required = true,
                            example = "1"
                    )
            }
    )
    @GetMapping("/venue/{venueId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByVenueId(@PathVariable Long venueId) {
        return ResponseEntity.ok(reservationUseCase.findByVenueId(venueId));
    }

    @Operation(
            summary = "Obtener reserva por código",
            description = "Devuelve una reserva específica, identificada por su código.",
            parameters = {
                    @Parameter(
                            name = "code",
                            description = "Código de la reserva para obtener toda su información",
                            required = true,
                            example = "ABC123"
                    )
            }
    )
    @GetMapping("/code/{code}")
    public ResponseEntity<Optional<ReservationDTO>> getReservationByCode(@PathVariable String code) {
        return ResponseEntity.ok(reservationUseCase.findByCode(code));
    }

    @GetMapping("/venue/{venueId}/date")
    public ResponseEntity<List<ReservationDTO>>
    getReservationsByVenueIdAndDate(@PathVariable Long venueId,
                                    @RequestParam(value = "date", required = false)
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {
        return ResponseEntity.ok(reservationUseCase.findByVenueIdAndDate(venueId, date));
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
            description = "Actualiza el estado de una reserva a INACTIVO usando su ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID de la reserva que se desea cancelar",
                            required = true,
                            example = "12"
                    )
            }
    )
    @PutMapping("/{id}/canceled")
    public ResponseEntity<MessageResponse> canceledReservation(@PathVariable Long id) {
        reservationUseCase.updateStatus(id, Status.INACTIVE);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse("Se ha cancelado la reserva"));
    }

    @Operation(
            summary = "Verificar disponibilidad de reserva",
            description = "Recibe los datos de la reserva y verifica la disponibilidad para esa fecha y campo y devuelve la reserva."
    )
    @PostMapping("/availability")
    public ResponseEntity<Optional<ReservationDTO>> getReservationAvailability(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationAvailabilityUseCase
                        .reservationAvailability(reservationRequest)
                );
    }

    @GetMapping("/availability/hours")
    public ResponseEntity<List<TimeSlot>> getHoursAvailability(
            @RequestParam Long venueId,
            @RequestParam Long fieldId,
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date
    ) {
        return ResponseEntity.ok(reservationAvailabilityUseCase.getAvailableSlots(venueId, fieldId, date));
    }

}