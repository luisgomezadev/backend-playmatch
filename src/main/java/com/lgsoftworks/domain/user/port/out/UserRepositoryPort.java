package com.lgsoftworks.domain.user.port.out;

import com.lgsoftworks.application.user.dto.request.UserFilter;
import com.lgsoftworks.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepositoryPort {
    Page<User> searchUsers(UserFilter userFilter, Pageable pageable);
    Optional<User> findById(Long id);
    User save(User user);
    boolean deleteById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByCellphone(String cellphone);
}
