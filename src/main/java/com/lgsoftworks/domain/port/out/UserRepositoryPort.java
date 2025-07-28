package com.lgsoftworks.domain.port.out;

import com.lgsoftworks.domain.enums.Role;
import com.lgsoftworks.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    Page<User> findByRole(Role role, Pageable pageable);
    Optional<User> findById(Long id);
    User save(User user);
    boolean deleteById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByCellphone(String cellphone);
}
