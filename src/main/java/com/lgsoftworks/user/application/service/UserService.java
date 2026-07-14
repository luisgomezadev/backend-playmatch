package com.lgsoftworks.user.application.service;

import com.lgsoftworks.user.application.dto.mapper.UserModelMapper;
import com.lgsoftworks.user.application.dto.request.UserRequest;
import com.lgsoftworks.user.application.dto.response.UserDTO;
import com.lgsoftworks.user.application.port.in.UploadUserImageUseCase;
import com.lgsoftworks.user.application.port.in.UserUseCase;
import com.lgsoftworks.user.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.user.domain.model.User;
import com.lgsoftworks.user.domain.port.out.ImageUploaderPort;
import com.lgsoftworks.user.domain.port.out.UserRepositoryPort;
import com.lgsoftworks.user.domain.service.UserUniquenessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase, UploadUserImageUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final ImageUploaderPort imageUploader;
    private final UserUniquenessValidator uniquenessValidator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDTO> findById(Long id) {
        Optional<User> user = userRepositoryPort.findById(id);
        return user.map(UserModelMapper::toUserDTO);
    }

    @Override
    public UserDTO save(UserRequest userRequest) {
        uniquenessValidator.validate(userRequest.getEmail(), userRequest.getCellphone());
        String encoded = passwordEncoder.encode(userRequest.getPassword());
        User newUser = UserModelMapper.requestToModel(userRequest, encoded);
        User saved = userRepositoryPort.save(newUser);
        return UserModelMapper.toUserDTO(saved);
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        Optional<User> user = userRepositoryPort.findByEmail(email);
        return user.map(UserModelMapper::toUserDTO);
    }

    @Override
    public UserDTO uploadUserImage(Long userId, MultipartFile imageFile) {
        String imageUrl = imageUploader.uploadImage(imageFile);

        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new UserByIdNotFoundException(userId));

        user.changeImageUrl(imageUrl);
        userRepositoryPort.save(user);
        return UserModelMapper.toUserDTO(user);
    }

}
