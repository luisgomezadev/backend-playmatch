package com.lgsoftworks.domain.field.port.out;

import com.lgsoftworks.application.field.dto.request.FieldFilter;
import com.lgsoftworks.domain.field.model.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FieldRepositoryPort {
    Optional<Field> findById(Long id);

    Field save(Field field);

    void deleteById(Long id);
}
