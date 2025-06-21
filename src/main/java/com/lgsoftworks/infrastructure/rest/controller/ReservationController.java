package com.lgsoftworks.infrastructure.rest.controller;

import com.lgsoftworks.domain.enums.StatusReservation;
import com.lgsoftworks.domain.port.in.CountReservationUseCase;
import com.lgsoftworks.domain.port.in.ReservationAvailabilityUseCase;
import com.lgsoftworks.domain.port.in.ReservationUseCase;
import com.lgsoftworks.infrastructure.rest.dto.ReservationDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.ReservationRequest;
import com.lgsoftworks.infrastructure.rest.dto.MessageResponse;
import com.lgsoftworks.infrastructure.rest.dto.summary.ReservationAvailabilityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
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
    public ResponseEntity<ReservationDTO> saveReservation(@RequestBody ReservationRequest reservationRequest) {
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
        return ResponseEntity.status(HttpStatus.OK).body(countReservationUseCase.countActiveReservationsByTeam(teamId));
    }

    @GetMapping("/team/{teamId}/finished")
    public ResponseEntity<Long> getCountFinishedByTeam(@PathVariable Long teamId) {
        return ResponseEntity.status(HttpStatus.OK).body(countReservationUseCase.countFinishedReservationsByTeam(teamId));
    }

    @GetMapping("/field/{fieldId}/active")
    public ResponseEntity<Long> getCountActiveByField(@PathVariable Long fieldId) {
        return ResponseEntity.status(HttpStatus.OK).body(countReservationUseCase.countActiveReservationsByField(fieldId));
    }

    @GetMapping("/field/{fieldId}/finished")
    public ResponseEntity<Long> getCountFinishedByField(@PathVariable Long fieldId) {
        return ResponseEntity.status(HttpStatus.OK).body(countReservationUseCase.countFinishedReservationsByField(fieldId));
    }

    @PostMapping("/availability")
    public ResponseEntity<Optional<ReservationAvailabilityDTO>> getReservationAvailability(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationAvailabilityUseCase.reservationAvailability(reservationRequest));
    }

}
