package com.lgsoftworks.application.user.service;

import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.user.dto.request.UserFilter;
import com.lgsoftworks.application.user.dto.mapper.UserModelMapper;
import com.lgsoftworks.application.user.dto.request.UserRequest;
import com.lgsoftworks.application.user.dto.response.UserDTO;
import com.lgsoftworks.domain.common.port.out.CloudinaryImageUploaderPort;
import com.lgsoftworks.domain.exception.UserByEmailNotFoundException;
import com.lgsoftworks.domain.exception.UserByIdNotFoundException;
import com.lgsoftworks.domain.user.model.User;
import com.lgsoftworks.domain.user.port.in.UploadUserImageUseCase;
import com.lgsoftworks.domain.user.port.in.UserUseCase;
import com.lgsoftworks.domain.user.port.out.UserRepositoryPort;
import com.lgsoftworks.domain.user.validation.ValidateUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserService implements UserUseCase, UploadUserImageUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final CloudinaryImageUploaderPort imageUploader;
    private final ValidateUser validateUser;

    public UserService(UserRepositoryPort userRepositoryPort,
                             CloudinaryImageUploaderPort cloudinaryImageUploaderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.imageUploader = cloudinaryImageUploaderPort;
        this.validateUser = new ValidateUser(userRepositoryPort);
    }

    @Override
    public PageResponse<UserDTO> searchUsers(UserFilter filter, Pageable pageable) {

        Page<UserDTO> userDTOS = userRepositoryPort.searchUsers(filter, pageable)
                .map(UserModelMapper::toUserDTO);

        return new PageResponse<>(
                userDTOS.getContent(),
                userDTOS.getNumber(),
                userDTOS.getSize(),
                userDTOS.getTotalElements(),
                userDTOS.getTotalPages(),
                userDTOS.isLast()
        );
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        Optional<User> user = userRepositoryPort.findById(id);
        return user.map(UserModelMapper::toUserDTO);
    }

    @Override
    public UserDTO save(UserRequest userRequest) {
        validateUser.validate(userRequest.getEmail(), userRequest.getCellphone());
        User savedUser = userRepositoryPort.save(UserModelMapper.requestToModel(userRequest));
        return UserModelMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO update(UserRequest userRequest) {
        validateUser.validate(userRequest.getEmail(), userRequest.getCellphone());
        User updatedUser = userRepositoryPort.save(UserModelMapper.requestToModel(userRequest));
        return UserModelMapper.toUserDTO(updatedUser);
    }

    @Override
    public boolean deleteById(Long id) {
        return userRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        Optional<User> user = userRepositoryPort.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserByEmailNotFoundException(email);
        }
        return user.map(UserModelMapper::toUserDTO);
    }

    @Override
    public UserDTO uploadUserImage(Long userId, MultipartFile imageFile) {
        String imageUrl = imageUploader.uploadImage(imageFile);

        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new UserByIdNotFoundException(userId));

        user.setImageUrl(imageUrl);
        userRepositoryPort.save(user);
        return UserModelMapper.toUserDTO(user);
    }

}
