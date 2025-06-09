package com.lgsoftworks.infrastructure.rest.controller;

import com.lgsoftworks.infrastructure.rest.dto.RequestPlayerDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.RequestPlayerRequest;
import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.port.in.HandleRequestUseCase;
import com.lgsoftworks.domain.port.in.RequestPlayerUseCase;
import com.lgsoftworks.infrastructure.rest.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class RequestPlayerController {

    private final RequestPlayerUseCase requestPlayerUseCase;
    private final HandleRequestUseCase handleRequestUseCase;

    @PostMapping
    public ResponseEntity<RequestPlayerDTO> sendRequestPlayer(@RequestBody RequestPlayerRequest requestPlayerRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(requestPlayerUseCase.save(requestPlayerRequest));
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<Optional<RequestPlayerDTO>> getRequestByPlayer(@PathVariable Long playerId) {
        return ResponseEntity.ok().body(requestPlayerUseCase.findByPlayer(playerId));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<RequestPlayerDTO>> getRequestsByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok().body(requestPlayerUseCase.findByTeam(teamId));
    }

    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> handleRequest(@RequestParam StatusRequest statusRequest, @PathVariable Long id) {
        handleRequestUseCase.handleRequest(statusRequest, id);
        return ResponseEntity.ok().body(new MessageResponse("Solicitud respondida correctamente!"));
    }
}
