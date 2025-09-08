package com.lgsoftworks.domain.user.port.in;

import com.lgsoftworks.application.common.PageResponse;
import com.lgsoftworks.application.user.dto.request.UserFilter;
import com.lgsoftworks.application.user.dto.request.UserRequest;
import com.lgsoftworks.application.user.dto.response.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserUseCase {
    PageResponse<UserDTO> searchUsers(UserFilter userFilter, Pageable pageable);
    Optional<UserDTO> findById(Long id);
    UserDTO save(UserRequest userRequest);
    UserDTO update(UserRequest userRequest);
    boolean deleteById(Long id);
    Optional<UserDTO> findByEmail(String email);
}
