package com.lgsoftworks.infrastructure.adapter.out.persistence;

import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.model.FieldAdmin;
import com.lgsoftworks.domain.port.out.FieldAdminRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldAdminEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.FieldAdminDboMapper;
import com.lgsoftworks.infrastructure.adapter.out.persistence.repository.FieldAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FieldAdminJpaAdapter implements FieldAdminRepositoryPort {

    private final FieldAdminRepository fieldAdminRepository;

    @Override
    public List<FieldAdmin> findAll() {
        List<FieldAdminEntity> fieldAdminEntityList = fieldAdminRepository.findAll();
        return fieldAdminEntityList.stream()
                .map(FieldAdminDboMapper::toModel)
                .toList();
    }

    @Override
    public Optional<FieldAdmin> findById(Long id) {
        Optional<FieldAdminEntity> optionalAdmin = fieldAdminRepository.findById(id);
        return optionalAdmin.map(FieldAdminDboMapper::toModel);
    }

    @Override
    public FieldAdmin save(FieldAdmin fieldAdmin) {
        FieldAdminEntity savedAdmin = fieldAdminRepository.save(FieldAdminDboMapper.toDbo(fieldAdmin));
        return FieldAdminDboMapper.toModel(savedAdmin);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!fieldAdminRepository.existsById(id)) {
            throw new UserByIdNotFoundException(id);
        }
        fieldAdminRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<FieldAdmin> findByDocumentNumber(String documentNumber) {
        return fieldAdminRepository.findByDocumentNumber(documentNumber)
                .map(FieldAdminDboMapper::toModel);
    }

    @Override
    public Optional<FieldAdmin> findByEmail(String email) {
        return fieldAdminRepository.findByEmail(email)
                .map(FieldAdminDboMapper::toModel);
    }

    @Override
    public Optional<FieldAdmin> findByCellphone(String cellphone) {
        return fieldAdminRepository.findByCellphone(cellphone)
                .map(FieldAdminDboMapper::toModel);
    }
}
