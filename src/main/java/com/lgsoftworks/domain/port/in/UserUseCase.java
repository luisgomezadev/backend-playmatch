package com.lgsoftworks.domain.port.in;

import com.lgsoftworks.application.dto.request.UserRequest;
import com.lgsoftworks.application.dto.UserDTO;
import com.lgsoftworks.domain.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserUseCase {
    Page<UserDTO> findByRole(Role role, Pageable pageable);
    Optional<UserDTO> findById(Long id);
    UserDTO save(UserRequest userRequest);
    UserDTO update(UserRequest userRequest);
    boolean deleteById(Long id);
    Optional<UserDTO> findByEmail(String email);
    Optional<UserDTO> findByCellphone(String cellphone);
}
