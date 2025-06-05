package com.lgsoftworks.domain.port.out;

import com.lgsoftworks.domain.model.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminRepositoryPort {
    List<Admin> findAll();
    Optional<Admin> findById(Long id);
    Admin save(Admin person);
    Admin update(Admin person);
    boolean deleteById(Long id);
    Optional<Admin> findByDocumentNumber(String documentNumber);
    Optional<Admin> findByEmail(String email);
}
