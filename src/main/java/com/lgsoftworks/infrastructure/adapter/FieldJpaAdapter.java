package com.lgsoftworks.infrastructure.adapter;

import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.domain.port.out.FieldRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.entity.FieldEntity;
import com.lgsoftworks.infrastructure.adapter.mapper.FieldDboMapper;
import com.lgsoftworks.infrastructure.adapter.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FieldJpaAdapter implements FieldRepositoryPort {

    private final FieldRepository fieldRepository;

    @Override
    public List<Field> findAll() {
        List<FieldEntity> fieldEntityList = fieldRepository.findAll();
        return fieldEntityList.stream()
                .map(FieldDboMapper::toModel)
                .toList();
    }

    @Override
    public Optional<Field> findById(Long id) {
        return fieldRepository.findById(id)
                .map(FieldDboMapper::toModel);
    }

    @Override
    public Optional<Field> findByAdminId(Long id) {
        return fieldRepository.findByAdminId(id)
                .map(FieldDboMapper::toModel);
    }

    @Override
    public Field save(Field field) {
        FieldEntity savedEntity = fieldRepository.save(FieldDboMapper.toDbo(field));
        return FieldDboMapper.toModel(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        fieldRepository.deleteById(id);
    }

    @Override
    public boolean existsByAdminId(Long id) {
        return fieldRepository.existsByAdminId(id);
    }

}
