package com.lgsoftworks.field.application.service;

import com.lgsoftworks.field.application.dto.mapper.FieldModelMapper;
import com.lgsoftworks.field.application.dto.request.FieldRequest;
import com.lgsoftworks.field.application.dto.response.FieldDTO;
import com.lgsoftworks.field.application.port.in.FieldUseCase;
import com.lgsoftworks.field.domain.exception.FieldByIdNotFoundException;
import com.lgsoftworks.field.domain.model.Field;
import com.lgsoftworks.field.domain.port.out.FieldRepositoryPort;
import com.lgsoftworks.venue.domain.model.VenueId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldService implements FieldUseCase {

    private final FieldRepositoryPort fieldRepositoryPort;
    private final FieldModelMapper fieldModelMapper;

    @Override
    public FieldDTO save(FieldRequest request) {
        Field field = Field.create(
                request.getName(),
                request.getFieldType(),
                request.getHourlyRate(),
                new VenueId(request.getVenueId())
        );
        Field saved = fieldRepositoryPort.save(field);
        return fieldModelMapper.toDTO(saved);
    }

    @Override
    public FieldDTO findById(Long id) {
        return fieldRepositoryPort.findById(id)
                .map(fieldModelMapper::toDTO)
                .orElseThrow(() -> new FieldByIdNotFoundException(id));
    }

    @Override
    public List<FieldDTO> findByVenueId(Long venueId) {
        return fieldRepositoryPort.findByVenueId(venueId).stream()
                .map(fieldModelMapper::toDTO)
                .toList();
    }

    @Override
    public FieldDTO updatePrice(Long fieldId, BigDecimal newHourlyRate) {
        Field field = fieldRepositoryPort.findById(fieldId)
                .orElseThrow(() -> new FieldByIdNotFoundException(fieldId));
        field.updatePrice(newHourlyRate);
        Field saved = fieldRepositoryPort.save(field);
        return fieldModelMapper.toDTO(saved);
    }

    @Override
    public FieldDTO update(FieldRequest request, Long fieldId) {
        Field field = fieldRepositoryPort.findById(fieldId)
                .orElseThrow(() -> new FieldByIdNotFoundException(fieldId));
        field.updateFieldType(request.getFieldType());
        field.updatePrice(request.getHourlyRate());
        field.rename(request.getName());
        Field updated = fieldRepositoryPort.save(field);
        return fieldModelMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        Field field = fieldRepositoryPort.findById(id)
                .orElseThrow(() -> new FieldByIdNotFoundException(id));
        field.deactivate();
        fieldRepositoryPort.save(field);
    }
}