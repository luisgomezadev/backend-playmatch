package com.lgsoftworks.domain.port.out;

import com.lgsoftworks.domain.model.FieldAdmin;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FieldAdminRepositoryPort {
    List<FieldAdmin> findAll();
    Optional<FieldAdmin> findById(Long id);
    FieldAdmin save(FieldAdmin fieldAdmin);
    boolean deleteById(Long id);
    Optional<FieldAdmin> findByDocumentNumber(String documentNumber);
    Optional<FieldAdmin> findByEmail(String email);
    Optional<FieldAdmin> findByCellphone(String cellphone);
}
