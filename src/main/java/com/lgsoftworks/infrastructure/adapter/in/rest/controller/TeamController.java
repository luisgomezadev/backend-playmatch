package com.lgsoftworks.infrastructure.adapter.in.rest.controller;

import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.application.dto.summary.TeamSummaryDTO;
import com.lgsoftworks.domain.port.in.AssignTeamUseCase;
import com.lgsoftworks.domain.port.in.DeletePlayerTeamUseCase;
import com.lgsoftworks.domain.port.in.TeamUseCase;
import com.lgsoftworks.application.dto.TeamDTO;
import com.lgsoftworks.application.dto.request.TeamRequest;
import com.lgsoftworks.domain.port.in.UploadTeamImageUseCase;
import com.lgsoftworks.infrastructure.adapter.in.rest.dto.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
@Validated
public class TeamController {

    private final TeamUseCase teamUseCase;
    private final AssignTeamUseCase assignTeamUseCase;
    private final UploadTeamImageUseCase uploadTeamImageUseCase;
    private final DeletePlayerTeamUseCase deletePlayerTeamUseCase;

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getTeams() {
        return ResponseEntity.ok().body(teamUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TeamDTO>> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamUseCase.findById(id));
    }

    @PostMapping
    public ResponseEntity<TeamSummaryDTO> saveTeam(@Valid @RequestBody TeamRequest teamRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamUseCase.save(teamRequest));
    }

    @PostMapping("/{teamId}/member/{userId}")
    public ResponseEntity<MessageResponse> addMemberToTeam(
            @PathVariable Long teamId,
            @PathVariable Long userId) {
        assignTeamUseCase.addMemberToTeam(teamId, userId);
        return ResponseEntity.ok().body(new MessageResponse("Jugador inscrito al equipo correctamente"));
    }

    @PostMapping("delete/{playerId}/team/{teamId}")
    public ResponseEntity<MessageResponse> deletePlayerOfTeam(
            @PathVariable Long teamId,
            @PathVariable Long playerId) {
        deletePlayerTeamUseCase.deletePlayerOfTeam(teamId, playerId);
        return ResponseEntity.ok().body(new MessageResponse("Jugador eliminado del equipo correctamente"));
    }

    @GetMapping("/teams")
    public ResponseEntity<List<TeamDTO>> getTeamsByCity(@RequestParam String city) {
        return ResponseEntity.ok().body(teamUseCase.findByCity(city));
    }

    @PutMapping
    public ResponseEntity<TeamSummaryDTO> updateTeam(@Valid @RequestBody TeamRequest teamRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(teamUseCase.update(teamRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteTeamById(@PathVariable Long id){
        teamUseCase.deleteById(id);
        return ResponseEntity.ok().body(new MessageResponse("Equipo eliminado exitosamente!"));
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<TeamDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        TeamDTO updatedTeam = uploadTeamImageUseCase.uploadTeamImage(id, file);
        return ResponseEntity.ok(updatedTeam);
    }

}
