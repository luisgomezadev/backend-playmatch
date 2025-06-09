package com.lgsoftworks.application.service;

import com.lgsoftworks.application.mapper.AdminModelMapper;
import com.lgsoftworks.application.mapper.PersonModelMapper;
import com.lgsoftworks.infrastructure.rest.dto.AdminDTO;
import com.lgsoftworks.infrastructure.rest.dto.request.AdminRequest;
import com.lgsoftworks.infrastructure.rest.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.domain.exception.PersonByDocumentNotFoundException;
import com.lgsoftworks.domain.exception.PersonByEmailNotFoundException;
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
    public PersonSummaryDTO save(AdminRequest adminRequest) {
        validatePerson.validate(adminRequest.getDocumentNumber(),
                adminRequest.getEmail());
        Admin savedPerson = adminRepositoryPort.save(AdminModelMapper.toModelRequest(adminRequest));
        return PersonModelMapper.toPersonSummary(savedPerson);
    }

    @Override
    public PersonSummaryDTO update(AdminRequest adminRequest) {
        Admin updatedAdmin = adminRepositoryPort.update(AdminModelMapper.toModelRequest(adminRequest));
        return PersonModelMapper.toPersonSummary(updatedAdmin);
    }

    @Override
    public boolean deleteById(Long id) {
        return adminRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<AdminDTO> findByDocumentNumber(String documentNumber) {
        Optional<Admin> optionalAdmin = adminRepositoryPort.findByDocumentNumber(documentNumber);
        if (optionalAdmin.isEmpty()) {
            throw new PersonByDocumentNotFoundException(documentNumber);
        }
        return optionalAdmin.map(AdminModelMapper::toDTO);
    }

    @Override
    public Optional<AdminDTO> findByEmail(String email) {
        Optional<Admin> optionalAdmin = adminRepositoryPort.findByEmail(email);
        if (optionalAdmin.isEmpty()) {
            throw new PersonByEmailNotFoundException(email);
        }
        return optionalAdmin.map(AdminModelMapper::toDTO);
    }

}
