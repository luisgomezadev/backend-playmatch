package com.lgsoftworks.field.application.port.in;

import com.lgsoftworks.field.application.dto.request.FieldRequest;
import com.lgsoftworks.field.application.dto.response.FieldDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface FieldUseCase {
    FieldDTO save(FieldRequest request);
    Optional<FieldDTO> findById(Long id);
    List<FieldDTO> findByVenueId(Long venueId);
    FieldDTO updatePrice(Long fieldId, BigDecimal newHourlyRate);
    void deleteById(Long id);
}