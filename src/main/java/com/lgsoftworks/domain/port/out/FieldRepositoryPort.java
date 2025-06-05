package com.lgsoftworks.domain.port.out;

import com.lgsoftworks.domain.model.Field;

import java.util.List;
import java.util.Optional;

public interface FieldRepositoryPort {
    List<Field> findAll();
    Optional<Field> findById(Long id);
    Field save(Field field);
    Field update(Field field);
    void deleteById(Long id);
    boolean existsByAdminId(Long id);
}
