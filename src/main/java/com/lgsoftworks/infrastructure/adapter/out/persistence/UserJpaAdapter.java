package com.lgsoftworks.infrastructure.adapter.out.persistence;

import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.user.model.User;
import com.lgsoftworks.domain.user.port.out.UserRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.UserDboMapper;
import com.lgsoftworks.infrastructure.adapter.out.persistence.repository.UserRepository;
import com.lgsoftworks.infrastructure.adapter.out.persistence.specifications.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        return optionalUser.map(UserDboMapper::toModel);
    }

    @Override
    public User save(User player) {
        UserEntity savedUser = userRepository.save(UserDboMapper.toDbo(player));
        return UserDboMapper.toModel(savedUser);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserByIdNotFoundException(id);
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserDboMapper::toModel);
    }

    @Override
    public Optional<User> findByCellphone(String cellphone) {
        return userRepository.findByCellphone(cellphone)
                .map(UserDboMapper::toModel);
    }

}
