package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.FieldModelMapper;
import com.lgsoftworks.application.mapper.TeamModelMapper;
import com.lgsoftworks.domain.exception.FieldByIdNotFoundException;
import com.lgsoftworks.domain.exception.TeamByIdNotFoundException;
import com.lgsoftworks.domain.model.FieldAdmin;
import com.lgsoftworks.domain.model.Team;
import com.lgsoftworks.domain.port.in.FieldUseCase;
import com.lgsoftworks.application.dto.FieldDTO;
import com.lgsoftworks.application.dto.request.FieldRequest;
import com.lgsoftworks.domain.exception.UserAlreadyAssignedAsAdminException;
import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.model.Field;
import com.lgsoftworks.domain.port.in.UploadFieldImageUseCase;
import com.lgsoftworks.domain.port.out.CloudinaryImageUploaderPort;
import com.lgsoftworks.domain.port.out.FieldAdminRepositoryPort;
import com.lgsoftworks.domain.port.out.FieldRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldService implements FieldUseCase, UploadFieldImageUseCase {

    private final FieldRepositoryPort fieldRepositoryPort;
    private final FieldAdminRepositoryPort fieldAdminRepositoryPort;
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
        FieldAdmin fieldAdmin = fieldAdminRepositoryPort.findById(fieldRequest.getAdminId())
                .orElseThrow(() -> new UserByIdNotFoundException(fieldRequest.getAdminId()));

        if (existsByAdminId(fieldAdmin.getId())) {
            throw new UserAlreadyAssignedAsAdminException(fieldAdmin.getId());
        }

        Field field = FieldModelMapper.toModelRequest(fieldRequest);
        field.setFieldAdmin(fieldAdmin);
        Field savedField = fieldRepositoryPort.save(field);

        fieldAdmin.setField(savedField);
        fieldAdminRepositoryPort.save(fieldAdmin);

        return FieldModelMapper.toDTO(savedField);
    }

    @Override
    public FieldDTO update(FieldRequest fieldRequest) {
        Long fieldId = fieldRequest.getId();
        Field existingField = fieldRepositoryPort.findById(fieldId)
                .orElseThrow(() -> new FieldByIdNotFoundException(fieldId));

        FieldAdmin fieldAdmin = fieldAdminRepositoryPort.findById(fieldRequest.getAdminId())
                .orElseThrow(() -> new UserByIdNotFoundException(fieldRequest.getAdminId()));

        // Prevenir cambiar a un admin que ya tiene otra cancha (si se está cambiando)
        if (!existingField.getFieldAdmin().getId().equals(fieldAdmin.getId()) && existsByAdminId(fieldAdmin.getId())) {
            throw new UserAlreadyAssignedAsAdminException(fieldAdmin.getId());
        }

        Field field = FieldModelMapper.toModelRequest(fieldRequest);

        Field updatedField = fieldRepositoryPort.save(field);
        return FieldModelMapper.toDTO(updatedField);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Field field = fieldRepositoryPort.findById(id)
                .orElseThrow(() -> new FieldByIdNotFoundException(id));

        if (field.getFieldAdmin() != null) {
            field.getFieldAdmin().setField(null);
        }

        // Actualizar el administrador sin la cancha
        fieldAdminRepositoryPort.save(field.getFieldAdmin());

        fieldRepositoryPort.deleteById(id);
    }

    @Override
    public boolean existsByAdminId(Long id) {
        return fieldRepositoryPort.existsByAdminId(id);
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
