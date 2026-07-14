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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldService implements FieldUseCase {

    private final FieldRepositoryPort fieldRepositoryPort;

    @Override
    public FieldDTO save(FieldRequest request) {
        Field field = Field.create(
                request.getName(),
                request.getFieldType(),
                request.getHourlyRate(),
                new VenueId(request.getVenueId())
        );
        Field saved = fieldRepositoryPort.save(field);
        return FieldModelMapper.toDTO(saved);
    }

    @Override
    public Optional<FieldDTO> findById(Long id) {
        return fieldRepositoryPort.findById(id).map(FieldModelMapper::toDTO);
    }

    @Override
    public List<FieldDTO> findByVenueId(Long venueId) {
        return fieldRepositoryPort.findByVenueId(venueId).stream()
                .map(FieldModelMapper::toDTO)
                .toList();
    }

    @Override
    public FieldDTO updatePrice(Long fieldId, BigDecimal newHourlyRate) {
        Field field = fieldRepositoryPort.findById(fieldId)
                .orElseThrow(() -> new FieldByIdNotFoundException(fieldId));
        field.updatePrice(newHourlyRate);
        Field saved = fieldRepositoryPort.save(field);
        return FieldModelMapper.toDTO(saved);
    }

    @Override
    public void deleteById(Long id) {
        fieldRepositoryPort.findById(id).orElseThrow(() -> new FieldByIdNotFoundException(id));
        fieldRepositoryPort.deleteById(id);
    }
}