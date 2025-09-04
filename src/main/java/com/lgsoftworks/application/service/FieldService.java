package com.lgsoftworks.application.service;

import com.lgsoftworks.application.dto.PageResponse;
import com.lgsoftworks.application.dto.request.FieldFilter;
import com.lgsoftworks.application.mapper.FieldModelMapper;
import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.enums.Status;
import com.lgsoftworks.domain.exception.FieldByIdNotFoundException;
import com.lgsoftworks.domain.exception.UserTypeNotAllowedToCreateFieldException;
import com.lgsoftworks.domain.model.User;
import com.lgsoftworks.domain.port.in.FieldUseCase;
import com.lgsoftworks.application.dto.FieldDTO;
import com.lgsoftworks.application.dto.request.FieldRequest;
import com.lgsoftworks.domain.exception.UserAlreadyAssignedAsAdminException;
import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.domain.port.in.UploadFieldImageUseCase;
import com.lgsoftworks.domain.port.out.CloudinaryImageUploaderPort;
import com.lgsoftworks.domain.port.out.UserRepositoryPort;
import com.lgsoftworks.domain.port.out.FieldRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldService implements FieldUseCase, UploadFieldImageUseCase {

    private final FieldRepositoryPort fieldRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final CloudinaryImageUploaderPort imageUploader;

    @Override
    public List<FieldDTO> findAll() {
        List<Field> fieldList = fieldRepositoryPort.findAll();
        return fieldList.stream().map(FieldModelMapper::toDTO).toList();
    }

    @Override
    public Optional<FieldDTO> findById(Long id) {
        return fieldRepositoryPort.findById(id)
                .map(FieldModelMapper::toDTO);
    }

    @Override
    public Optional<FieldDTO> findByAdminId(Long id) {
        return fieldRepositoryPort.findByAdminId(id)
                .map(FieldModelMapper::toDTO);
    }

    @Override
    public List<FieldDTO> findAllActive() {
        List<Field> fieldList = fieldRepositoryPort.findAllActive();
        return fieldList.stream().map(FieldModelMapper::toDTO).toList();
    }

    @Override
    public FieldDTO save(FieldRequest fieldRequest) {
        User user = userRepositoryPort.findById(fieldRequest.getAdminId())
                .orElseThrow(() -> new UserByIdNotFoundException(fieldRequest.getAdminId()));

        if (user.getRole() != Role.FIELD_ADMIN) {
            throw new UserTypeNotAllowedToCreateFieldException(user);
        }

        if (existsByAdminId(user.getId())) {
            throw new UserAlreadyAssignedAsAdminException(user.getId());
        }

        Field field = FieldModelMapper.toModelRequest(fieldRequest);
        field.setAdmin(user);
        Field savedField = fieldRepositoryPort.save(field);

        return FieldModelMapper.toDTO(savedField);
    }

    @Override
    public FieldDTO update(FieldRequest fieldRequest) {
        Long fieldId = fieldRequest.getId();
        Field existingField = fieldRepositoryPort.findById(fieldId)
                .orElseThrow(() -> new FieldByIdNotFoundException(fieldId));

        User user = userRepositoryPort.findById(fieldRequest.getAdminId())
                .orElseThrow(() -> new UserByIdNotFoundException(fieldRequest.getAdminId()));

        if (user.getRole() != Role.FIELD_ADMIN) {
            throw new UserTypeNotAllowedToCreateFieldException(user);
        }

        // Prevenir cambiar a un admin que ya tiene otra cancha (si se está cambiando)
        if (!existingField.getAdmin().getId().equals(user.getId()) && existsByAdminId(user.getId())) {
            throw new UserAlreadyAssignedAsAdminException(user.getId());
        }

        Field field = FieldModelMapper.toModelRequest(fieldRequest);
        field.setAdmin(user);
        Field updatedField = fieldRepositoryPort.save(field);

        return FieldModelMapper.toDTO(updatedField);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Field field = fieldRepositoryPort.findById(id)
                .orElseThrow(() -> new FieldByIdNotFoundException(id));

        fieldRepositoryPort.deleteById(field.getId());
    }

    @Override
    public boolean existsByAdminId(Long id) {
        return fieldRepositoryPort.existsByAdminId(id);
    }

    @Override
    public PageResponse<FieldDTO> searchFields(FieldFilter filter, Pageable pageable) {
        Page<FieldDTO> fieldDTOS = fieldRepositoryPort.searchFields(filter, pageable)
                .map(FieldModelMapper::toDTO);

        return new PageResponse<>(
                fieldDTOS.getContent(),
                fieldDTOS.getNumber(),
                fieldDTOS.getSize(),
                fieldDTOS.getTotalElements(),
                fieldDTOS.getTotalPages(),
                fieldDTOS.isLast()
        );
    }

    public String uploadFieldImage(MultipartFile file) {
        return imageUploader.uploadImage(file);
    }

    @Override
    public FieldDTO uploadFieldImage(Long fieldId, MultipartFile imageFile) {
        String imageUrl = imageUploader.uploadImage(imageFile);

        Field field = fieldRepositoryPort.findById(fieldId)
                .orElseThrow(() -> new FieldByIdNotFoundException(fieldId));

        field.setImageUrl(imageUrl);
        fieldRepositoryPort.save(field);
        return FieldModelMapper.toDTO(field);
    }
}
