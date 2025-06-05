package com.lgsoftworks.infrastructure.rest.controller;

import com.lgsoftworks.domain.port.in.FieldUseCase;
import com.lgsoftworks.domain.dto.FieldDTO;
import com.lgsoftworks.domain.dto.request.FieldRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/field")
@RequiredArgsConstructor
public class FieldController {

    private final FieldUseCase fieldUseCase;

    @GetMapping
    public ResponseEntity<List<FieldDTO>> getFields() {
        return ResponseEntity.ok().body(fieldUseCase.findAll());
    }

    @PostMapping
    public ResponseEntity<FieldDTO> saveField(@RequestBody FieldRequest fieldRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fieldUseCase.save(fieldRequest));
    }
}
