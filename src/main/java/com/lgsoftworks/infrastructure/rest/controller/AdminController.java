package com.lgsoftworks.infrastructure.rest.controller;

import com.lgsoftworks.infrastructure.rest.dto.AdminDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.AdminRequest;
import com.lgsoftworks.infrastructure.rest.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.domain.port.in.AdminUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/person/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminUseCase adminUseCase;

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAdmins() {
        return ResponseEntity.ok(adminUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AdminDTO>> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(adminUseCase.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonSummaryDTO> saveAdmin(@RequestBody AdminRequest adminRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminUseCase.save(adminRequest));
    }
}
