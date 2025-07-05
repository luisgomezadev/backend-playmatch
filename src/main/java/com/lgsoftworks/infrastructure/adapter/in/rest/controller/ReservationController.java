package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.port.in.CountReservationUseCase;
import com.lgsoftworks.domain.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.port.in.ReservationUseCase;
import com.lgsoftworks.application.dto.ReservationDTO;
import com.lgsoftworks.application.dto.request.ReservationRequest;
import com.lgsoftworks.infrastructure.adapter.in.rest.dto.MessageResponse;
import com.lgsoftworks.application.dto.summary.ReservationAvailabilityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
@Validated
public class ReservationController {

    private final ReservationUseCase reservationUseCase;
    private final CountReservationUseCase countReservationUseCase;
    private final ReservationAvailabilityUseCase reservationAvailabilityUseCase;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        return ResponseEntity.ok(reservationUseCase.findAll());
    }

    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByFieldId(@PathVariable Long fieldId) {
        return ResponseEntity.ok(reservationUseCase.findByFieldId(fieldId));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByTeamId(@PathVariable Long teamId) {
        return ResponseEntity.ok(reservationUseCase.findByTeamId(teamId));
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> saveReservation(@Valid @RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationUseCase.save(reservationRequest));
    }

    @PutMapping("/{id}/status/canceled")
    public ResponseEntity<MessageResponse> canceledStatus(@PathVariable Long id) {
        reservationUseCase.updateStatus(id, StatusReservation.CANCELED);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse("Se ha actualizado el estado de la reserva"));
    }

    @PutMapping("/{id}/status/finalize")
    public ResponseEntity<MessageResponse> finalizeStatus(@PathVariable Long id) {
        reservationUseCase.updateStatus(id, StatusReservation.FINISHED);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse("Se ha actualizado el estado de la reserva"));
    }

    @GetMapping("/team/{teamId}/active")
    public ResponseEntity<Long> getCountActiveByTeam(@PathVariable Long teamId) {
        return ResponseEntity.status(HttpStatus.OK).body(countReservationUseCase.countReservationsByTeamAndStatus(teamId, StatusReservation.ACTIVE));
    }

    @GetMapping("/team/{teamId}/canceled")
    public ResponseEntity<Long> getCountCanceledByTeam(@PathVariable Long teamId) {
        return ResponseEntity.status(HttpStatus.OK).body(countReservationUseCase.countReservationsByTeamAndStatus(teamId, StatusReservation.CANCELED));
    }

    @GetMapping("/team/{teamId}/finished")
    public ResponseEntity<Long> getCountFinishedByTeam(@PathVariable Long teamId) {
        return ResponseEntity.status(HttpStatus.OK).body(countReservationUseCase.countReservationsByTeamAndStatus(teamId, StatusReservation.FINISHED));
    }

    @GetMapping("/field/{fieldId}/active")
    public ResponseEntity<Long> getCountActiveByField(@PathVariable Long fieldId) {
        return ResponseEntity.status(HttpStatus.OK).body(countReservationUseCase.countReservationsByFieldAndStatus(fieldId, StatusReservation.ACTIVE));
    }

    @GetMapping("/field/{fieldId}/canceled")
    public ResponseEntity<Long> getCountCanceledByField(@PathVariable Long fieldId) {
        return ResponseEntity.status(HttpStatus.OK).body(countReservationUseCase.countReservationsByFieldAndStatus(fieldId, StatusReservation.CANCELED));
    }

    @GetMapping("/field/{fieldId}/finished")
    public ResponseEntity<Long> getCountFinishedByField(@PathVariable Long fieldId) {
        return ResponseEntity.status(HttpStatus.OK).body(countReservationUseCase.countReservationsByFieldAndStatus(fieldId, StatusReservation.FINISHED));
    }

    @PostMapping("/availability")
    public ResponseEntity<Optional<ReservationAvailabilityDTO>> getReservationAvailability(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationAvailabilityUseCase.reservationAvailability(reservationRequest));
    }

}