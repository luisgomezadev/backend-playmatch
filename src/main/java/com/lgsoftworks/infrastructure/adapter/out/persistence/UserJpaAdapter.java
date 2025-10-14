package com.lgsoftworks.infrastructure.adapter.out.persistence;

import com.lgsoftworks.domain.user.model.User;
import com.lgsoftworks.domain.user.port.out.UserRepositoryPort;
import com.lgsoftworks.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.lgsoftworks.infrastructure.adapter.out.persistence.mapper.UserDboMapper;
import com.lgsoftworks.infrastructure.adapter.out.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
