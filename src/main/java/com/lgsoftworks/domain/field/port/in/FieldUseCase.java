package com.lgsoftworks.domain.field.port.in;

import com.lgsoftworks.application.field.dto.request.FieldRequest;
import com.lgsoftworks.application.field.dto.response.FieldDTO;

import java.util.Optional;

public interface FieldUseCase {
    Optional<FieldDTO> findById(Long id);

    FieldDTO save(FieldRequest fieldRequest);

    void deleteById(Long id);
}
