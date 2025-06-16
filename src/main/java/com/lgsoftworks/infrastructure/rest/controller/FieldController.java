package com.lgsoftworks.infrastructure.rest.controller;

import com.lgsoftworks.domain.port.in.FieldUseCase;
import com.lgsoftworks.infrastructure.rest.dto.FieldDTO;
import com.lgsoftworks.infrastructure.rest.dto.MessageResponse;
import com.lgsoftworks.infrastructure.rest.dto.request.FieldRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/field")
@RequiredArgsConstructor
public class FieldController {

    private final FieldUseCase fieldUseCase;

    @GetMapping
    public ResponseEntity<List<FieldDTO>> getFields() {
        return ResponseEntity.ok().body(fieldUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FieldDTO>> getFieldById(@PathVariable Long id) {
        return ResponseEntity.ok(fieldUseCase.findById(id));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Optional<FieldDTO>> getFieldByAdminId(@PathVariable Long id) {
        return ResponseEntity.ok(fieldUseCase.findByAdminId(id));
    }

    @PostMapping
    public ResponseEntity<FieldDTO> saveField(@RequestBody FieldRequest fieldRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fieldUseCase.save(fieldRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteTeamById(@PathVariable Long id){
        fieldUseCase.deleteById(id);
        return ResponseEntity.ok().body(new MessageResponse("Campo eliminado exitosamente!"));
    }

    @PutMapping
    public ResponseEntity<FieldDTO> updateField(@RequestBody FieldRequest fieldRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(fieldUseCase.update(fieldRequest));
    }
}
