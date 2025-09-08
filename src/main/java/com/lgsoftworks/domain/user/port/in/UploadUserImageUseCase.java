package com.lgsoftworks.domain.user.port.in;

import com.lgsoftworks.application.user.dto.response.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadUserImageUseCase {
    UserDTO uploadUserImage(Long userId, MultipartFile imageFile);
}
