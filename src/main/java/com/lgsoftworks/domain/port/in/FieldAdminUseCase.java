package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.FieldAdminDTO;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface FieldAdminUseCase {
    List<FieldAdminDTO> findAll();
    Optional<FieldAdminDTO> findById(Long id);
    UserDTO save(UserRequest fieldAdminRequest);
    UserDTO update(UserRequest fieldAdminRequest);
    boolean deleteById(Long id);
    Optional<FieldAdminDTO> findByDocumentNumber(String documentNumber);
    Optional<FieldAdminDTO> findByEmail(String email);
    Optional<FieldAdminDTO> findByCellphone(String cellphone);
}
