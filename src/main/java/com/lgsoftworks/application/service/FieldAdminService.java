package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.FieldAdminModelMapper;
import com.lgsoftworks.application.mapper.UserModelMapper;
import com.lgsoftworks.application.dto.FieldAdminDTO;
import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.domain.exception.UserByCellphoneNotFoundException;
import com.lgsoftworks.domain.exception.UserByDocumentNotFoundException;
import com.lgsoftworks.domain.exception.UserByEmailNotFoundException;
import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.model.FieldAdmin;
import com.lgsoftworks.domain.port.in.FieldAdminUseCase;
import com.lgsoftworks.domain.port.in.UploadAdminImageUseCase;
import com.lgsoftworks.domain.port.out.CloudinaryImageUploaderPort;
import com.lgsoftworks.domain.port.out.FieldAdminRepositoryPort;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.domain.validation.ValidateUser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class FieldAdminService implements FieldAdminUseCase, UploadAdminImageUseCase {
    private final FieldAdminRepositoryPort fieldAdminRepositoryPort;
    private final CloudinaryImageUploaderPort imageUploader;
    private final ValidateUser validateUser;

    private final Path uploadDir = Paths.get("uploads");

    public FieldAdminService(FieldAdminRepositoryPort fieldAdminRepositoryPort,
                             PlayerRepositoryPort playerRepositoryPort,
                             CloudinaryImageUploaderPort cloudinaryImageUploaderPort) {
        this.fieldAdminRepositoryPort = fieldAdminRepositoryPort;
        this.imageUploader = cloudinaryImageUploaderPort;
        this.validateUser = new ValidateUser(fieldAdminRepositoryPort, playerRepositoryPort);
    }

    @Override
    public List<FieldAdminDTO> findAll() {
        List<FieldAdmin> fieldAdminList = fieldAdminRepositoryPort.findAll();
        return fieldAdminList.stream()
                .map(FieldAdminModelMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<FieldAdminDTO> findById(Long id) {
        Optional<FieldAdmin> optionalAdmin = fieldAdminRepositoryPort.findById(id);
        return optionalAdmin.map(FieldAdminModelMapper::toDTO);
    }

    @Override
    public UserDTO save(UserRequest fieldAdminRequest) {
        validateUser.validate(fieldAdminRequest.getDocumentNumber(),
                fieldAdminRequest.getEmail(), fieldAdminRequest.getCellphone());
        FieldAdmin savedUser = fieldAdminRepositoryPort.save(FieldAdminModelMapper.toModelRequest(fieldAdminRequest));
        return UserModelMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO update(UserRequest fieldAdminRequest) {
        validateUser.validate(fieldAdminRequest.getDocumentNumber(),
                fieldAdminRequest.getEmail(), fieldAdminRequest.getCellphone());
        FieldAdmin updatedFieldAdmin = fieldAdminRepositoryPort.save(FieldAdminModelMapper.toModelRequest(fieldAdminRequest));
        return UserModelMapper.toUserDTO(updatedFieldAdmin);
    }

    @Override
    public boolean deleteById(Long id) {
        return fieldAdminRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<FieldAdminDTO> findByDocumentNumber(String documentNumber) {
        Optional<FieldAdmin> optionalAdmin = fieldAdminRepositoryPort.findByDocumentNumber(documentNumber);
        if (optionalAdmin.isEmpty()) {
            throw new UserByDocumentNotFoundException(documentNumber);
        }
        return optionalAdmin.map(FieldAdminModelMapper::toDTO);
    }

    @Override
    public Optional<FieldAdminDTO> findByEmail(String email) {
        Optional<FieldAdmin> optionalAdmin = fieldAdminRepositoryPort.findByEmail(email);
        if (optionalAdmin.isEmpty()) {
            throw new UserByEmailNotFoundException(email);
        }
        return optionalAdmin.map(FieldAdminModelMapper::toDTO);
    }

    @Override
    public Optional<FieldAdminDTO> findByCellphone(String cellphone) {
        Optional<FieldAdmin> optionalAdmin = fieldAdminRepositoryPort.findByCellphone(cellphone);
        if (optionalAdmin.isEmpty()) {
            throw new UserByCellphoneNotFoundException(cellphone);
        }
        return optionalAdmin.map(FieldAdminModelMapper::toDTO);
    }

    @Override
    public UserDTO uploadAdminImage(Long adminId, MultipartFile imageFile) {
        String imageUrl = imageUploader.uploadImage(imageFile);

        FieldAdmin fieldAdmin = fieldAdminRepositoryPort.findById(adminId)
                .orElseThrow(() -> new UserByIdNotFoundException(adminId));

        fieldAdmin.setImageUrl(imageUrl);
        fieldAdminRepositoryPort.save(fieldAdmin);
        return UserModelMapper.toUserDTO(fieldAdmin);
    }

}
