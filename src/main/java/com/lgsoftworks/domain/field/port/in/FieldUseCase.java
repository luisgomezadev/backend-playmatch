package com.lgsoftworks.domain.field.port.in;

import com.lgsoftworks.application.field.dto.response.FieldDTO;
import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.field.dto.request.FieldFilter;
import com.lgsoftworks.application.field.dto.request.FieldRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FieldUseCase {
    PageResponse<FieldDTO> searchFields(FieldFilter filter, Pageable pageable);
    Optional<FieldDTO> findById(Long id);
    Optional<FieldDTO> findByAdminId(Long id);
    List<FieldDTO> findAllActive();
    FieldDTO save(FieldRequest fieldRequest);
    FieldDTO update(FieldRequest fieldRequest);
    void deleteById(Long id);
    boolean existsByAdminId(Long id);
}
