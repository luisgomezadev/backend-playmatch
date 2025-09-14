package com.lgsoftworks.infrastructure.adapter.out.persistence;

import com.lgsoftworks.domain.field.model.Field;
import com.lgsoftworks.domain.field.port.out.FieldRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.FieldEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.FieldDboMapper;
import com.lgsoftworks.infrastructure.adapter.out.persistence.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FieldJpaAdapter implements FieldRepositoryPort {

    private final FieldRepository fieldRepository;

    @Override
    public Optional<Field> findById(Long id) {
        return fieldRepository.findById(id)
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

}
