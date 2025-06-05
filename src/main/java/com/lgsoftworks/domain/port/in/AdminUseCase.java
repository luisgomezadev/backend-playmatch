package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.domain.dto.AdminDTO;
import com.lgsoftworks.domain.dto.request.AdminRequest;
import com.lgsoftworks.domain.dto.summary.PersonSummaryDTO;

import java.util.List;
import java.util.Optional;

public interface AdminUseCase {
    List<AdminDTO> findAll();
    Optional<AdminDTO> findById(Long id);
    PersonSummaryDTO save(AdminRequest adminRequest);
    PersonSummaryDTO update(AdminRequest adminRequest);
    boolean deleteById(Long id);
    Optional<AdminDTO> findByDocumentNumber(String documentNumber);
    Optional<AdminDTO> findByEmail(String email);
}
