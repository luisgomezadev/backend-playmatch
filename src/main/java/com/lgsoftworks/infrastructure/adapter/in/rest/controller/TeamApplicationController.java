package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.application.dto.TeamApplicationDTO;
import com.lgsoftworks.application.dto.request.TeamApplicationRequest;
import com.lgsoftworks.domain.enums.StatusRequest;
import com.lgsoftworks.domain.port.in.HandleRequestUseCase;
import com.lgsoftworks.domain.port.in.TeamApplicationUseCase;
import com.lgsoftworks.infrastructure.adapter.in.rest.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
@Validated
public class TeamApplicationController {

    private final TeamApplicationUseCase teamApplicationUseCase;
    private final HandleRequestUseCase handleRequestUseCase;

    @PostMapping
    public ResponseEntity<TeamApplicationDTO> sendTeamApplication(@RequestBody TeamApplicationRequest teamApplicationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(teamApplicationUseCase.save(teamApplicationRequest));
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<TeamApplicationDTO>> getTeamApplicationByPlayer(@PathVariable Long playerId) {
        return ResponseEntity.ok().body(teamApplicationUseCase.findByPlayer(playerId));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<TeamApplicationDTO>> getTeamApplicationByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok().body(teamApplicationUseCase.findByTeam(teamId));
    }

    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> handleTeamApplication(@RequestParam StatusRequest statusRequest, @PathVariable Long id) {
        handleRequestUseCase.handleRequest(statusRequest, id);
        return ResponseEntity.ok().body(new MessageResponse("Solicitud respondida correctamente!"));
    }
}
