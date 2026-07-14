package com.lgsoftworks.field.domain.port.out;

import com.lgsoftworks.field.domain.model.Field;

import java.util.List;
import java.util.Optional;

public interface FieldRepositoryPort {
    Field save(Field field);
    Optional<Field> findById(Long id);
    List<Field> findByVenueId(Long venueId);
    void deleteById(Long id);
}