package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.FieldModelMapper;
import com.lgsoftworks.domain.exception.FieldByIdNotFoundException;
import com.lgsoftworks.domain.model.Admin;
import com.lgsoftworks.domain.port.in.FieldUseCase;
import com.lgsoftworks.application.dto.FieldDTO;
import com.lgsoftworks.application.dto.request.FieldRequest;
import com.lgsoftworks.domain.exception.UserAlreadyAssignedAsAdminException;
import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.domain.port.out.AdminRepositoryPort;
import com.lgsoftworks.domain.port.out.FieldRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FieldService implements FieldUseCase {

    private final FieldRepositoryPort fieldRepositoryPort;
    private final AdminRepositoryPort adminRepositoryPort;

    @Override
    public List<FieldDTO> findAll() {
        List<Field> fieldList = fieldRepositoryPort.findAll();
        return fieldList.stream().map(FieldModelMapper::toDTO).toList();
    }

    @Override
    public Optional<FieldDTO> findById(Long id) {
        return fieldRepositoryPort.findById(id)
                .map(FieldModelMapper::toDTO);
    }

    @Override
    public Optional<FieldDTO> findByAdminId(Long id) {
        return fieldRepositoryPort.findByAdminId(id)
                .map(FieldModelMapper::toDTO);
    }

    @Override
    public FieldDTO save(FieldRequest fieldRequest) {
        Admin admin = adminRepositoryPort.findById(fieldRequest.getAdminId())
                .orElseThrow(() -> new UserByIdNotFoundException(fieldRequest.getAdminId()));

        if (existsByAdminId(admin.getId())) {
            throw new UserAlreadyAssignedAsAdminException(admin.getId());
        }

        Field field = FieldModelMapper.toModelRequest(fieldRequest);
        field.setAdmin(admin);
        Field savedField = fieldRepositoryPort.save(field);

        admin.setField(savedField);

        return FieldModelMapper.toDTO(savedField);
    }

    @Override
    public FieldDTO update(FieldRequest fieldRequest) {
        Long fieldId = fieldRequest.getId();
        if (fieldId == null) {
            throw new IllegalArgumentException("El ID del campo es obligatorio para actualizar.");
        }

        Field existingField = fieldRepositoryPort.findById(fieldId)
                .orElseThrow(() -> new FieldByIdNotFoundException(fieldId));

        Admin admin = adminRepositoryPort.findById(fieldRequest.getAdminId())
                .orElseThrow(() -> new UserByIdNotFoundException(fieldRequest.getAdminId()));

        // Prevenir cambiar a un admin que ya tiene otra cancha (si se está cambiando)
        if (!existingField.getAdmin().getId().equals(admin.getId()) && existsByAdminId(admin.getId())) {
            throw new UserAlreadyAssignedAsAdminException(admin.getId());
        }

        existingField.setName(fieldRequest.getName());
        existingField.setAddress(fieldRequest.getAddress());
        existingField.setCity(fieldRequest.getCity());
        existingField.setHourlyRate(fieldRequest.getHourlyRate());
        existingField.setOpeningHour(fieldRequest.getOpeningHour());
        existingField.setClosingHour(fieldRequest.getClosingHour());
        existingField.setStatus(fieldRequest.getStatus());
        existingField.setAdmin(admin);

        Field updatedField = fieldRepositoryPort.save(existingField);
        return FieldModelMapper.toDTO(updatedField);
    }

    @Override
    public void deleteById(Long id) {
        if (fieldRepositoryPort.findById(id).isEmpty()) {
            throw new FieldByIdNotFoundException(id);
        }
        fieldRepositoryPort.deleteById(id);
    }

    @Override
    public boolean existsByAdminId(Long id) {
        return fieldRepositoryPort.existsByAdminId(id);
    }
}
