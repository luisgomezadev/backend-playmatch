package com.lgsoftworks.infrastructure.adapter;

import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.model.Admin;
import com.lgsoftworks.domain.port.out.AdminRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.entity.AdminEntity;
import com.lgsoftworks.infrastructure.adapter.mapper.AdminDboMapper;
import com.lgsoftworks.infrastructure.adapter.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminJpaAdapter implements AdminRepositoryPort {

    private final AdminRepository adminRepository;

    @Override
    public List<Admin> findAll() {
        List<AdminEntity> adminEntityList = adminRepository.findAll();
        return adminEntityList.stream()
                .map(AdminDboMapper::toModel)
                .toList();
    }

    @Override
    public Optional<Admin> findById(Long id) {
        Optional<AdminEntity> optionalAdmin = adminRepository.findById(id);
        return optionalAdmin.map(AdminDboMapper::toModel);
    }

    @Override
    public Admin save(Admin admin) {
        AdminEntity savedAdmin = adminRepository.save(AdminDboMapper.toDbo(admin));
        return AdminDboMapper.toModel(savedAdmin);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new UserByIdNotFoundException(id);
        }
        adminRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Admin> findByDocumentNumber(String documentNumber) {
        return adminRepository.findByDocumentNumber(documentNumber)
                .map(AdminDboMapper::toModel);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email)
                .map(AdminDboMapper::toModel);
    }
}
