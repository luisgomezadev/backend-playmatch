package com.lgsoftworks.domain.port.out;

import com.lgsoftworks.domain.model.Field;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface FieldRepositoryPort {
    List<Field> findAll();
    Optional<Field> findById(Long id);
    Optional<Field> findByAdminId(Long id);
    List<Field> findAllActive();
    Field save(Field field);
    void deleteById(Long id);
    boolean existsByAdminId(Long id);
}
