package com.lgsoftworks.domain.field.port.out;

import com.lgsoftworks.domain.field.model.Field;

import java.util.Optional;

public interface FieldRepositoryPort {
    Optional<Field> findById(Long id);

    Field save(Field field);

    void deleteById(Long id);
}
