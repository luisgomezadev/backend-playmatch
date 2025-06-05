package com.lgsoftworks.infrastructure.rest.controller;

import com.lgsoftworks.domain.port.in.ReservationUseCase;
import com.lgsoftworks.domain.dto.ReservationDTO;
import com.lgsoftworks.domain.dto.request.ReservationRequest;
import com.lgsoftworks.infrastructure.rest.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationUseCase reservationUseCase;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        return ResponseEntity.ok(reservationUseCase.findAll());
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> saveReservation(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationUseCase.save(reservationRequest));
    }

    @PostMapping("/finalize/{id}")
    public ResponseEntity<MessageResponse> finalizeReservationById(@PathVariable Long id) {
        reservationUseCase.finalizeReservation(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse("Reserva con ID " + id + " finalizada"));
    }

}
