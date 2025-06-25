package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.AdminModelMapper;
import com.lgsoftworks.application.mapper.UserModelMapper;
import com.lgsoftworks.application.dto.AdminDTO;
import com.lgsoftworks.application.dto.request.AdminRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.domain.exception.UserByDocumentNotFoundException;
import com.lgsoftworks.domain.exception.UserByEmailNotFoundException;
import com.lgsoftworks.domain.model.Admin;
import com.lgsoftworks.domain.port.in.AdminUseCase;
import com.lgsoftworks.domain.port.out.AdminRepositoryPort;
import com.lgsoftworks.domain.port.out.PlayerRepositoryPort;
import com.lgsoftworks.domain.validation.ValidatePerson;

import java.util.List;
import java.util.Optional;

public class AdminService implements AdminUseCase {
    private final AdminRepositoryPort adminRepositoryPort;
    private final ValidatePerson validatePerson;

    public AdminService(AdminRepositoryPort adminRepositoryPort, PlayerRepositoryPort playerRepositoryPort) {
        this.adminRepositoryPort = adminRepositoryPort;
        this.validatePerson = new ValidatePerson(adminRepositoryPort, playerRepositoryPort);
    }

    @Override
    public List<AdminDTO> findAll() {
        List<Admin> adminList = adminRepositoryPort.findAll();
        return adminList.stream()
                .map(AdminModelMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<AdminDTO> findById(Long id) {
        Optional<Admin> optionalAdmin = adminRepositoryPort.findById(id);
        return optionalAdmin.map(AdminModelMapper::toDTO);
    }

    @Override
    public UserDTO save(AdminRequest adminRequest) {
        validatePerson.validate(adminRequest.getDocumentNumber(),
                adminRequest.getEmail());
        Admin savedPerson = adminRepositoryPort.save(AdminModelMapper.toModelRequest(adminRequest));
        return UserModelMapper.toPersonSummary(savedPerson);
    }

    @Override
    public UserDTO update(AdminRequest adminRequest) {
        Admin updatedAdmin = adminRepositoryPort.save(AdminModelMapper.toModelRequest(adminRequest));
        return UserModelMapper.toPersonSummary(updatedAdmin);
    }

    @Override
    public boolean deleteById(Long id) {
        return adminRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<AdminDTO> findByDocumentNumber(String documentNumber) {
        Optional<Admin> optionalAdmin = adminRepositoryPort.findByDocumentNumber(documentNumber);
        if (optionalAdmin.isEmpty()) {
            throw new UserByDocumentNotFoundException(documentNumber);
        }
        return optionalAdmin.map(AdminModelMapper::toDTO);
    }

    @Override
    public Optional<AdminDTO> findByEmail(String email) {
        Optional<Admin> optionalAdmin = adminRepositoryPort.findByEmail(email);
        if (optionalAdmin.isEmpty()) {
            throw new UserByEmailNotFoundException(email);
        }
        return optionalAdmin.map(AdminModelMapper::toDTO);
    }

}
