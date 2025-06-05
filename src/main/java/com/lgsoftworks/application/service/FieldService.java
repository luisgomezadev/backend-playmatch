package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.FieldModelMapper;
import com.lgsoftworks.domain.model.Admin;
import com.lgsoftworks.domain.port.in.FieldUseCase;
import com.lgsoftworks.domain.dto.FieldDTO;
import com.lgsoftworks.domain.dto.request.FieldRequest;
import com.lgsoftworks.domain.exception.PersonAlreadyAssignedAsAdminException;
import com.lgsoftworks.domain.exception.PersonByIdNotFoundException;
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
    public FieldDTO save(FieldRequest fieldRequest) {
        Admin admin = adminRepositoryPort.findById(fieldRequest.getAdmin().getId())
                .orElseThrow(() -> new PersonByIdNotFoundException(fieldRequest.getAdmin().getId()));

        if (existsByAdminId(admin.getId())) {
            throw new PersonAlreadyAssignedAsAdminException(admin.getId());
        }

        Field field = FieldModelMapper.toModelRequest(fieldRequest);
        field.setAdmin(admin);
        Field savedField = fieldRepositoryPort.save(field);

        admin.setField(savedField);

        return FieldModelMapper.toDTO(savedField);
    }

    @Override
    public FieldDTO update(FieldRequest fieldRequest) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        fieldRepositoryPort.deleteById(id);
    }

    @Override
    public boolean existsByAdminId(Long id) {
        return fieldRepositoryPort.existsByAdminId(id);
    }
}
