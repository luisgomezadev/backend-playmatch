package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.FieldDTO;
import com.lgsoftworks.application.dto.request.FieldRequest;
import com.lgsoftworks.domain.model.Field;

import java.util.List;
import java.util.Optional;

public interface FieldUseCase {
    List<FieldDTO> findAll();
    Optional<FieldDTO> findById(Long id);
    Optional<FieldDTO> findByAdminId(Long id);
    List<FieldDTO> findAllActive();
    FieldDTO save(FieldRequest fieldRequest);
    FieldDTO update(FieldRequest fieldRequest);
    void deleteById(Long id);
    boolean existsByAdminId(Long id);
}
