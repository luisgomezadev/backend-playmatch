package com.lgsoftworks.infrastructure.adapter.out.persistence.repository;

import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Page<UserEntity> findByRole(Role role, Pageable pageable);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByCellphone(String cellphone);
}
