package com.lgsoftworks.application.field.service;

import com.lgsoftworks.application.field.dto.mapper.FieldModelMapper;
import com.lgsoftworks.application.field.dto.request.FieldRequest;
import com.lgsoftworks.application.field.dto.response.FieldDTO;
import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.field.port.in.FieldUseCase;
import com.lgsoftworks.domain.field.port.out.FieldRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldService implements FieldUseCase {

    private final FieldRepositoryPort fieldRepositoryPort;

    @Override
    public Optional<FieldDTO> findById(Long id) {
        return fieldRepositoryPort.findById(id)
                .map(FieldModelMapper::toDTO);
    }

    @Override
    public FieldDTO save(FieldRequest fieldRequest) {
        Field field = FieldModelMapper.toModelRequest(fieldRequest);
        Field savedField = fieldRepositoryPort.save(field);
        return FieldModelMapper.toDTO(savedField);
    }

    @Override
    public void deleteById(Long id) {
        fieldRepositoryPort.deleteById(id);
    }
}
