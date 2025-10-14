package com.lgsoftworks.domain.user.port.out;

import com.lgsoftworks.domain.user.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findById(Long id);

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findByCellphone(String cellphone);
}
