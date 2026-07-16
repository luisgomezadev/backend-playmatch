package com.lgsoftworks.field.application.port.in;

import com.lgsoftworks.field.application.dto.request.FieldRequest;
import com.lgsoftworks.field.application.dto.response.FieldDTO;

import java.math.BigDecimal;
import java.util.List;

public interface FieldUseCase {
    FieldDTO save(FieldRequest request);
    FieldDTO findById(Long id);
    List<FieldDTO> findByVenueId(Long venueId);
    List<FieldDTO> findAllByVenueId(Long venueId);
    FieldDTO updatePrice(Long fieldId, BigDecimal newHourlyRate);
    FieldDTO update(FieldRequest request, Long fieldId);
    void deactivate(Long fieldId);
    void active(Long fieldId);
}